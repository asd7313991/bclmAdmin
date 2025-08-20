package org.example.system.system.homepage;

import org.example.service.system.homepage.HomePagePerDayVO;
import org.example.service.system.homepage.HomePageReqVO;
import org.example.service.system.homepage.HomePageVO;

import java.util.List;

/**
 * The interface Home page service.
 */
public interface HomePageService {
    /**
     * Ge total home page vo.
     *
     * @param reqVO the req vo
     * @return the home page vo
     */
    HomePageVO geTotal(HomePageReqVO reqVO);

    /**
     * Gets per day list.
     *
     * @param reqVO the req vo
     * @return the per day list
     */
    List<HomePagePerDayVO> getPerDayList(HomePageReqVO reqVO);
}
