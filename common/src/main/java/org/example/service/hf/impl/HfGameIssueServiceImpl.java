package org.example.service.hf.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.config.mybatisplus.LambdaQueryWrapperX;
import org.example.po.dream.hf.HfGameIssueDO;
import org.example.po.mapper.hf.HfGameIssueMapper;
import org.example.pojo.PageResult;
import org.example.service.hf.HfGameIssueService;
import org.example.vo.hf.gameIssue.HfGameIssueReqVo;
import org.example.vo.hf.gameIssue.KenoDrawThirdDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author D588
 * @description 针对表【hf_game_issue】的数据库操作Service实现
 * @createDate 2023-09-13 13:32:04
 */
@Service("hfGameIssueServiceImpl")
public class HfGameIssueServiceImpl extends ServiceImpl<HfGameIssueMapper, HfGameIssueDO>
        implements HfGameIssueService {

    @Override
    public PageResult<HfGameIssueDO> pageByParams(HfGameIssueReqVo pageVO) {
        return getBaseMapper().selectPage(pageVO);
    }

    @Override
    public void push(KenoDrawThirdDTO kenoDrawThirdDTO) {
        HfGameIssueDO hfGameIssueDO = getResult(kenoDrawThirdDTO.getDrawNbrs());
        hfGameIssueDO.setReceiveTime(new Date());
        DateTime parse = DateUtil.parse(kenoDrawThirdDTO.getDrawDate() + " " + kenoDrawThirdDTO.getDrawTime(), "yyyy-MM-dd HH:mm:ss");
        hfGameIssueDO.setOpenDrawTimeByPlayNow(parse);

        HfGameIssueDO hfGameIssueDO1 = getBaseMapper().selectOne(new LambdaQueryWrapperX<HfGameIssueDO>().eq(HfGameIssueDO::getLotteryid, 1).eq(HfGameIssueDO::getIssueCode, kenoDrawThirdDTO.getDrawNbr()));
        if (hfGameIssueDO1 == null) {
            hfGameIssueDO1 = new HfGameIssueDO();
            hfGameIssueDO1.setLotteryid("1");
            hfGameIssueDO1.setIssueCode(String.valueOf(kenoDrawThirdDTO.getDrawNbr()));
            hfGameIssueDO1.setLastIssueCode(String.valueOf(kenoDrawThirdDTO.getDrawNbr() - 1));
            hfGameIssueDO1.setSaleEndTime(DateUtil.offset(parse, DateField.SECOND, -30));
            hfGameIssueDO1.setSaleDrawTime(parse);
            getBaseMapper().insert(hfGameIssueDO1);
        } else {
            getBaseMapper().update(hfGameIssueDO, new LambdaUpdateWrapper<HfGameIssueDO>().eq(HfGameIssueDO::getIssueCode, kenoDrawThirdDTO.getDrawNbr()).eq(HfGameIssueDO::getLotteryid, 1).isNull(HfGameIssueDO::getNumberRecord));
        }


    }

    @Override
    public HfGameIssueDO findcurIssue(String lotteryId) {
        return getBaseMapper().findcurIssue(lotteryId);
    }

    @Override
    public List<HfGameIssueDO> findHistoryIssue(String lotteryId, String issueCode) {
        return getBaseMapper().findHistoryIssue(lotteryId, issueCode);
    }

    @Override
    public int drawGameIssue(String numberRecord, String sumRecord, String winRecord, String issue) {
        return getBaseMapper().drawGameIssue(numberRecord, sumRecord, winRecord, issue);
    }

    @Override
    public int countByIssue(String issue) {
        return getBaseMapper().countByIssue(issue);
    }

    @Override
    public void issueOpenClose(int issueStatus, String lotteryId, String issueCode) {
        getBaseMapper().issueOpenClose(issueStatus, lotteryId, issueCode);
    }

    @Override
    public HfGameIssueDO findIssueByStatus(String lotteryId, int value) {
        return getBaseMapper().findIssueByStatus(lotteryId, value);
    }

    @Override
    public int insertIssueIfAbsent(String issue, long lotteryIdLong, String lastIssue, Date saleStartTime, Date saleEndTime, Date saleDrawTime) {
        return getBaseMapper().insertIssueIfAbsent(issue, lotteryIdLong, lastIssue, saleStartTime, saleEndTime, saleDrawTime);
    }

    public HfGameIssueDO getResult(List<Integer> drawNbrs) {

        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        for (int a = 1; a <= 20; a++) {
            if (a == 1) {
                continue;
            } else if (a == 2 || a == 5 || a == 8 || a == 11 || a == 14 || a == 17) {
                sum1 += (int) drawNbrs.get(a - 1);
            } else if (a == 3 || a == 6 || a == 9 || a == 12 || a == 15 || a == 18) {
                sum2 += (int) drawNbrs.get(a - 1);
            } else if (a == 4 || a == 7 || a == 10 || a == 13 || a == 16 || a == 19) {
                sum3 += (int) drawNbrs.get(a - 1);
            }
        }
        HfGameIssueDO hfGameIssueDO = new HfGameIssueDO();

        hfGameIssueDO.setNumberRecord((sum1 % 10 + "," + sum2 % 10 + "," + sum3 % 10));
        hfGameIssueDO.setSumRecord(String.valueOf((sum1 % 10 + sum2 % 10 + sum3 % 10)));
        return hfGameIssueDO;
//            pc28.setBeginTime(DateUtil.date(pc28.getOpenTime()).toString());
//            pc28.setRet(OrderUtils.pc28Result(pc28.getResult()));
//            String resultStr = OrderUtils.getResultStr(pc28.getRet());
//            pc28Mapper.insertPc28(pc28);
//            StringBuffer sb = new StringBuffer();
//            sb.append("期号:").append(pc28.getNumber()).append("\n").append("结果：").append(pc28.getResult().replace(",","+")).append("=").append(pc28.getSumResult()).append("\n").append(resultStr);
//            myTelegramBot.sendMessage(sb.toString(),"Markdown");
//            String s = latestHistory(20);
//            myTelegramBot.sendMessage(s ,"Markdown");
//            Map<String,Integer> rtn = new LinkedHashMap();
//            int i2 = pc28Mapper.selectMaxCountByRet();
//            for (Integer retInt : getRetList()) {
//                String name = BetResultType.getName(retInt);
//                int i1 = pc28Mapper.selectCountByRet(Integer.toString(retInt));
//                if(name.matches("-?\\d+(\\.\\d+)?")){
//                    rtn.put( "数字"+name,i2-i1);
//                }else{
//                    rtn.put(name,i2-i1);
//                }
//            }
//            StringBuffer sb2 = new StringBuffer();
//            sb2.append("\uD83D\uDC51\uD83D\uDC51\uD83D\uDC51\uD83D\uDC51数据统计\uD83D\uDC51\uD83D\uDC51\uD83D\uDC51\uD83D\uDC51").append("\n");
//            for (Map.Entry<String,Integer> a:rtn.entrySet()) {
//                String key = a.getKey();
//                Integer value = a.getValue();
//                sb2.append("\uD83E\uDD20"+key+":").append("\t\t\t\t").append(""+value+"期"+""+"未开").append("\n");
//            }
//            sb2.append("\n\n").append("输入/n+局数  如/n10000查询10000局统计数据 ").append("\n")
//                    .append("输入/o+数字 如/o100 查询100局第一个数字为0-9 大小单双出现多少次").append("\n")
//                    .append("输入/w+数字 如/w100 查询100局第二个数字为为0-9  大小单双出现多少次").append("\n")
//                    .append("输入/t+数字 如/w100  查询100局第三个数字数字为为0-9 大小单双出现多少次").append("\n")
//                    .append("输入/count+数字 如/count100  查询100局大小单双尾数 数字尾数相关局数").append("\n");
//
//            myTelegramBot.sendMessage(sb2.toString(),"");
    }
}




