package org.example.game.game.constants;

/**
 * Created by yhj on 17/6/17.
 */
public class BattleRedisKey {

    public static final String BATTLE_FUTURE_CONFIG = "game:battle_future:confg";

    /**
     * 对战的房间缓存.
     */
    public static final String BATTLE_ROOM = "game:battle:"; // user + battleId

    public static final String BATTLE_ORDER = "game:battle:order";

    /**
     * 快速匹配缓存.
     */
    public static final String QUICK_LAUNCH = "game:quick:room"; // 快速匹配的房间队列
    public static final String QUICK_LAUNCH_CACHE = "game:quick:room:cache"; // 快速匹配的房间数据.


    public static final String QUICK_AGAINST = "game:quick:against"; // 快速匹配的应战者队列
    public static final String QUICK_AGAINST_CACHE = "game:quick:against:cache"; // 应战者拒绝的房间


    public static final String QUICK_ACTI = "game:quick:acti"; // 快速匹配的房间队列
    public static final String QUICK_ACTI_CACHE = "game:quick:acti:cache"; // 快速匹配的房间数据.


    public static final String QUICK_MATCH_RESULT = "game:quick:match"; // 应战者匹配成功的队列


    public static final String BATTLE_USER_SUBSCRIBE = "game:battle:subscribe"; // 应战者匹配成功的队列 key:userId,value:battleID


    public static final String BATTLE_AI = "game:battle:ai";//有机器人参与的对战队列


    /**
     * 房间里面的信息 hash 里面的filed  start
     * Key @see{BATTLE_ROOM}
     **/

    public static final String BATTLE_INFO = "battle_info";

    // 发起用户.
    public static final String BATTLE_LAUNCH_USER = "launch_user";
    public static final String BATTLE_LAUNCH_SCORE = "launch_score";
    public static final String BATTLE_LAUNCH_PRAISE = "launch_praise";
    public static final String BATTLE_LAUNCH_ORDER = "launch_order";

    // 迎战的人
    public static final String BATTLE_AGAINST_USER = "against_user";
    public static final String BATTLE_AGAINST_SCORE = "against_score";
    public static final String BATTLE_AGAINST_PRAISE = "against_praise";
    public static final String BATTLE_AGAINST_ORDER = "against_order";

    // 房间订阅
    public static final String BATTLE_SUBSCRIBE = "subscribe"; // 房间订阅

    public static final String BATTLE_LAST_ORDER_TIME = "last_order_time";  // 最后下单时间

    //
    public static final String BATTLE_QUICK_MATCH = "match_against"; //

    /** hash 里面的filed  end **/


    /**
     * 用于控制用户创建房间并发
     */
    public static final String BATTLE_USER_LIMIT = "game:battle:limituser:";

    public static final String BATTLE_ORDER_LIMIT = "game:battle:limitorder:";
    public static final String BATTLE_SETTLE_LIMIT = "game:battle:limitsettle:";


    public static String getBattleHashKey(Integer id) {
        return BattleRedisKey.BATTLE_ROOM + id;
    }
}
