package org.example.system.system.homepage;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.example.service.dream.DreamUFinanceIoService;
import org.example.service.dream.DreamUUserService;
import org.example.service.system.homepage.HomePagePerDayVO;
import org.example.service.system.homepage.HomePageReqVO;
import org.example.service.system.homepage.HomePageVO;
import org.example.vo.dream.financeIo.DreamUFinanceIoAmountPerDayVO;
import org.example.vo.dream.financeIo.DreamUFinanceIoAmountVO;
import org.example.vo.dream.user.DreamUUserCountPerDayVO;
import org.example.vo.dream.user.DreamUUserCountVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Home page service.
 */
@Service
public class HomePageServiceImpl implements HomePageService {
    @Resource
    private DreamUFinanceIoService dreamUFinanceIoService;
    @Resource
    private DreamUUserService dreamUUserService;

    @Override
    public HomePageVO geTotal(HomePageReqVO reqVO) {
        if (reqVO.getDays() == null) {
            reqVO.setDays(7);
        }
        HomePageVO homePageVO = new HomePageVO();

        LocalDateTime[] create = new LocalDateTime[2];
        LocalDateTime now = LocalDateTime.now();
        switch (reqVO.getType()) {
            case "all":
                create = null;
                break;
            case "today":
                create[0] = LocalDateTimeUtil.beginOfDay(now);
                create[1] = now;
                break;
            case "lastday":
                LocalDateTime localDateTime = now.minusDays(1);
                create[0] = LocalDateTimeUtil.beginOfDay(localDateTime);
                create[1] = LocalDateTimeUtil.endOfDay(localDateTime);
                break;
            case "7days":
                create[0] = now.minusDays(7);
                create[1] = now;
                break;
            case "15days":
                create[0] = now.minusDays(15);
                create[1] = now;
                break;
            case "1month":
                create[0] = now.minusMonths(1);
                create[1] = now;
                break;
            case "3month":
                create[0] = now.minusMonths(3);
                create[1] = now;
                break;
            default:
                return homePageVO;
        }

        reqVO.setCreateTime(create);

        //新增用户
        List<DreamUUserCountVO> dreamUUserCountVOS = dreamUUserService.countUserGroupBy(reqVO);
        Map<Integer, Long> userCountsMap = dreamUUserCountVOS.stream().collect(Collectors.toMap(DreamUUserCountVO::getType, DreamUUserCountVO::getCounts));
        Long counts0 = userCountsMap.getOrDefault(0, 0L);
        Long counts1 = userCountsMap.getOrDefault(1, 0L);
        homePageVO.setNewRegularUsers(counts0);
        homePageVO.setNewVIPUsers(counts1);

        //充提金额
        List<DreamUFinanceIoAmountVO> dreamUFinanceIoAmountVOS = dreamUFinanceIoService.sumAmount(reqVO);
        Map<Integer, Double> financeIoAmountMap = dreamUFinanceIoAmountVOS.stream().collect(Collectors.toMap(DreamUFinanceIoAmountVO::getType, DreamUFinanceIoAmountVO::getAmount));
        Double sum10 = financeIoAmountMap.getOrDefault(10, 0.0);
        Double sum20 = financeIoAmountMap.getOrDefault(20, 0.0);
        homePageVO.setRechargeAmount(BigDecimal.valueOf(sum10));
        homePageVO.setWithdrawalAmount(BigDecimal.valueOf(sum20));

        //注单
        homePageVO.setTotalEarnings(BigDecimal.ZERO);
        homePageVO.setBetAmount(BigDecimal.ZERO);
        homePageVO.setBetUsers(0L);

        return homePageVO;
    }

    @Override
    public List<HomePagePerDayVO> getPerDayList(HomePageReqVO reqVO) {
        if (reqVO.getDays() == null) {
            reqVO.setDays(7);
        }
        List<LocalDate> localDates = this.generateDateList(reqVO.getDays());

        //新增用户
        List<DreamUUserCountPerDayVO> dreamUUserCountVOS = dreamUUserService.countUserPerDayGroupBy(reqVO);
        if (CollectionUtil.isEmpty(dreamUUserCountVOS)) {
            dreamUUserCountVOS = new ArrayList<>();
        }
        Map<Integer, Map<LocalDate, Long>> userCountsMap = dreamUUserCountVOS.stream()
                .collect(Collectors.groupingBy(DreamUUserCountPerDayVO::getType,
                        Collectors.toMap(DreamUUserCountPerDayVO::getPerDate,
                                DreamUUserCountPerDayVO::getCounts)));

        //充提金额
        List<DreamUFinanceIoAmountPerDayVO> dreamUFinanceIoAmountPerDayVOS = dreamUFinanceIoService.sumPerDayAmount(reqVO);
        if (CollectionUtil.isEmpty(dreamUFinanceIoAmountPerDayVOS)) {
            dreamUFinanceIoAmountPerDayVOS = new ArrayList<>();
        }
        Map<Integer, Map<LocalDate, Double>> financeIoAmountMap = dreamUFinanceIoAmountPerDayVOS.stream()
                .collect(Collectors.groupingBy(DreamUFinanceIoAmountPerDayVO::getType,
                        Collectors.toMap(DreamUFinanceIoAmountPerDayVO::getPerDate,
                                DreamUFinanceIoAmountPerDayVO::getAmount)));

        return localDates.stream().map(x -> {
            HomePagePerDayVO homePagePerDayVO = new HomePagePerDayVO();

            homePagePerDayVO.setPerDate(x);

            //新增用户
            Optional.ofNullable(userCountsMap.get(0)).ifPresent(y -> homePagePerDayVO.setNewRegularUsers(y.getOrDefault(x, 0L)));
            Optional.ofNullable(userCountsMap.get(1)).ifPresent(y -> homePagePerDayVO.setNewVIPUsers(y.getOrDefault(x, 0L)));

            //充提金额
            Optional.ofNullable(financeIoAmountMap.get(10)).ifPresent(y -> homePagePerDayVO.setRechargeAmount(BigDecimal.valueOf(y.getOrDefault(x, 0.0))));
            Optional.ofNullable(financeIoAmountMap.get(20)).ifPresent(y -> homePagePerDayVO.setWithdrawalAmount(BigDecimal.valueOf(y.getOrDefault(x, 0.0))));

            //注单
            homePagePerDayVO.setTotalEarnings(BigDecimal.ZERO);
            homePagePerDayVO.setBetAmount(BigDecimal.ZERO);
            homePagePerDayVO.setBetUsers(0L);

            return homePagePerDayVO;
        }).collect(Collectors.toList());
    }

    /**
     * Generate date list list.
     *
     * @param numDays the num days
     * @return the list
     */
    public List<LocalDate> generateDateList(int numDays) {
        List<LocalDate> dateList = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        LocalDate startDate = currentDate.minusDays(numDays - 1);
        for (int i = 0; i < numDays; i++) {
            dateList.add(startDate.plusDays(i));
        }

        return dateList;
    }
}
