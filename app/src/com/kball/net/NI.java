package com.kball.net;

import java.io.File;
import java.util.HashMap;


import com.google.gson.Gson;
import com.kball.C;
import com.kball.util.SPUtil;

/**
 * 所有的网络接口类
 */
public class NI {

    // 网络接口地址
    private static final String DO_MAIN = getUrl1();
    private static final String DO_MAIN_Match = getUrl2();
    private static final String DO_MAIN_Refrech = getUrl3();
    private static final String DO_MAIN_PROVICE = getUrl4();

    private static final boolean isTest = true;

    private static String getUrl1() {
        if (isTest) {
            return "http://47.93.119.150:2498/";
        } else {
            return "http://101.201.145.244:8081/";
        }
    }

    private static String getUrl2() {
        if (isTest) {
            return "http://47.93.119.150:2498/";
        } else {
            return "http://101.201.145.244:8085/";
        }
    }

    private static String getUrl3() {
        if (isTest) {
            return "http://47.93.119.150:2498/";
        } else {
            return "http://101.201.145.244:8083/";
        }
    }

    private static String getUrl4() {
        if (isTest) {
            return "http://47.93.119.150:2498/";
        } else {
            return "http://101.201.145.244:8089/";
        }
    }


    public static UrlParamsBean getFans(String s, String s1) {
        String url = DO_MAIN + "user/fans/inte/fansList";
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("pageNum", s);
        map.put("pageSize", s1);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getConcern(String s, String s1) {

        String url = DO_MAIN + "user/fans/inte/focusList";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("pageNum", s);
        map.put("pageSize", s1);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean focusTeamList(String s, String s1) {

        String url = DO_MAIN_Refrech + "team/teamFans/inte/focusTeamList";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("pageNum", s);
        map.put("pageSize", s1);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getMyInfo() {
        String url = DO_MAIN + "user/center/inte/selectUserInfo";
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("user_id", SPUtil.getInstance().getString(C.SP.USER_ID, ""));
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getMyInfo(String user_id) {
        String url = DO_MAIN + "user/center/inte/selectUserInfo";
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("user_id", user_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getTJ() {
        String url = DO_MAIN_Refrech + "team/index/inte/indexRefreshRecommendedTeam";
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("number", "5");
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getTJ1(String num) {
        String url = DO_MAIN_Refrech + "team/index/inte/indexRefreshRecommendedTeam";
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("number", num);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean login(String identity_type, String identifier, String password) {
        String url = DO_MAIN + "user/user/token";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("identity_type", identity_type);
        map.put("identifier", identifier);
        map.put("credential", password);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean login(String identity_type, String identifier) {
        String url = DO_MAIN + "user/user/token";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("identity_type", identity_type);
        map.put("identifier", identifier);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean register(String phone, String verifyCode) {
        String url = DO_MAIN + "user/user/register";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("phone", phone);
        map.put("verifyCode", verifyCode);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getVerifycode(String type, String phone) {
        String url = DO_MAIN + "user/general/getVerifycode";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        map.put("phone", phone);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean perfectInfo(String user_id, String credential, String nickname, String portrait) {
        String url = DO_MAIN + "user/user/perfectInfo";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", user_id);
        map.put("credential", credential);
        map.put("nickname", nickname);
        map.put("portrait", portrait);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean updatePasswordByPhone(String phone, String verifycode, String password) {
        String url = DO_MAIN + "user/user/updatePasswordByPhone";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("phone", phone);
        map.put("verifycode", verifycode);
        map.put("password", password);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean bound(String phone, String verifycode, String openId, String openIdType, String
            accessToken, String nickname, String portrait) {
        String url = DO_MAIN + "user/user/bound";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("phone", phone);
        map.put("verifycode", verifycode);
        map.put("openId", openId);
        map.put("openIdType", openIdType);
        map.put("accessToken", accessToken);
        map.put("nickname", nickname);
        map.put("portrait", portrait);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean selectGameList(String pageNum, String pageSize, String game_status, String game_time,
                                               String team_id) {
        String url = DO_MAIN_Match + "league/tournament/inte/selectGameList";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("game_status", game_status);
        map.put("game_time", game_time);
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean gameFiltrateConditions() {
        String url = DO_MAIN_Match + "league/tournament/inte/gameFiltrateConditions";
        HashMap<String, String> map = new HashMap<String, String>();
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean match_saichen(String league_id, int i) {
        String url = DO_MAIN_Match + "league/schedule/inte/selectSchedule";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("league_id", league_id);
        map.put("rounds", i + "");
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getMatchInfo(String league_id) {
        String url = DO_MAIN_Match + "league/league/inte/leagueInfo";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("league_id", league_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean selectCrunchies(String league_id, String type) {
        String url = DO_MAIN_Match + "league/schedule/inte/selectCrunchies";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("league_id", league_id);
        map.put("type", type);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean selectCrunchies(String league_id) {
        String url = DO_MAIN_Match + "league/schedule/inte/selectCrunchies";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("league_id", league_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getVideo(String s, String game_id) {
        String url = DO_MAIN_Match + "league/video/selectVideo";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", game_id);
        map.put("classify", s);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getVideo(String s, String game_id, String pageNum, String pageSize) {
        String url = DO_MAIN_Match + "league/video/selectVideo";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", game_id);
        map.put("classify", s);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean refreshToken() {
        String url = DO_MAIN + "user/user/refreshToken";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", SPUtil.getInstance().getString(C.SP.USER_ID, ""));
        map.put("refresh_token", SPUtil.getInstance().getString(C.SP.REFRESH_TOKEN, ""));
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean selectGameInfo(String s) {
        String url = DO_MAIN_Match + "league/tournament/inte/selectGameInfo";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("game_id", s);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean selectGrand(String s) {
        String url = DO_MAIN_Match + "league/gameGrand/inte/selectGrand";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("game_id", s);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getjifen(String league_id) {
        String url = DO_MAIN_Match + "league/schedule/inte/selectTabelle";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("league_id", league_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean meTeamLeagueList(String pageNum, String pageSize, String team_id, String status) {
        String url = DO_MAIN_Match + "league/league/inte/meTeamLeagueList";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("team_id", team_id);
        map.put("status", status);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean meTeamLeagueList(String pageNum, String pageSize, String team_id,
                                                 String status, String game_system, String type) {
        String url = DO_MAIN_Match + "league/league/inte/meTeamLeagueList";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("team_id", team_id);
        map.put("status", status);
        map.put("game_system", game_system);
        map.put("type", type);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean indexTeamInfo(String league_id) {
        String url = DO_MAIN_Refrech + "team/team/inte/selectTeamDetailedDate";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", league_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean teamRecord(String pageNum, String pageSize, String team_id) {
        String url = DO_MAIN_Refrech + "team/team/inte/teamRecord";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getDuihui() {
        String url = DO_MAIN_Refrech + "team/index/inte/indexTeamList";
        HashMap<String, String> map = new HashMap<String, String>();
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getRankInfo(String team_id) {
        String url = DO_MAIN_Refrech + "team/index/inte/indexTeamInfo";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }


    public static UrlParamsBean leagueListScreen() {
        ///101.201.145.244:8085/league/inte/leagueListScreen
        String url = DO_MAIN_Match + "league/league/leagueListScreen";
        HashMap<String, String> map = new HashMap<String, String>();
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean leagueListScreen(String type) {
        ///101.201.145.244:8085/league/inte/leagueListScreen
        String url = DO_MAIN_Match + "league/league/leagueListScreen";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("type",type);
        return new UrlParamsBean(url, map);
    }


    public static UrlParamsBean upLoadImg(File file) {
        String url = "http://101.201.145.244:8082/uploads/more";
        HashMap<String, String> map = new HashMap<String, String>();
        UrlParamsBean urlParamsBean = new UrlParamsBean(url, map);
        urlParamsBean.setFileParam("file", file);
        return urlParamsBean;
    }

    public static UrlParamsBean selectTeamMember(String team_id) {
        String url = DO_MAIN_Refrech + "team/team/inte/selectTeamMember";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getVersion(String appVersionName) {

        String url = DO_MAIN + "user/version/v1/checkVersion";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("house_version_number", appVersionName);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean updatePhone(String worn_phone, String new_phone, String verifyCode) {

        String url = DO_MAIN + "user/center/inte/updatePhone";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("worn_phone", worn_phone);
        map.put("new_phone", new_phone);
        map.put("verifyCode", verifyCode);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean updatePassword(String worn_password, String new_password, String type) {
        String url = DO_MAIN + "user/center/inte/updatePassword";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("worn_password", worn_password);
        map.put("new_password", new_password);
        map.put("type", type);
        return new UrlParamsBean(url, map);
    }

    ///101.201.145.244:8083/team/inte/selectTeamList
    public static UrlParamsBean selectTeamList() {
        String url = DO_MAIN_Refrech + "team/team/inte/selectTeamList";
        HashMap<String, String> map = new HashMap<String, String>();
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean selectProvince() {
        String url = DO_MAIN_PROVICE + "system/system/selectProvince";
        HashMap<String, String> map = new HashMap<String, String>();
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean selectCity(String str) {
        String url = DO_MAIN_PROVICE + "system/system/selectCity";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("province_id", str);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean selectArea(String city_id) {
        String url = DO_MAIN_PROVICE + "system/system/selectArea";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("city_id", city_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean insertTeam(String imageURL, String mRankName, String mTypeName, String proviceName,
                                           String cityName, String areaName, String mPlace, String mTime, String
                                                   mTag, String bgImgURL) {
        String url = DO_MAIN_Refrech + "team/team/inte/insertTeam";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_name", mRankName);
        map.put("badge", imageURL);
        map.put("team_type", mTypeName);
        map.put("establish_time", mTime);
        map.put("province", proviceName);
        map.put("city", cityName);
        map.put("area", areaName);
        map.put("home", mPlace);
        map.put("label", mTag);
        map.put("background", bgImgURL);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean updateTeamInfo(String team_id, String imageURL, String mRankName, String mTypeName,
                                               String proviceName, String cityName, String areaName, String mPlace,
                                               String mTime, String mTag, String bgImgURL) {
        String url = DO_MAIN_Refrech + "team/team/inte/updateTeamInfo";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        map.put("team_name", mRankName);
        map.put("badge", imageURL);
        map.put("team_type", mTypeName);
        map.put("establish_time", mTime);
        map.put("province", proviceName);
        map.put("city", cityName);
        map.put("area", areaName);
        map.put("home", mPlace);
        map.put("label", mTag);
        map.put("background", bgImgURL);
        return new UrlParamsBean(url, map);
    }

    /**
     * classify	是	int	创建类型 1：比赛 3：训练
     * game_name	否	String	比赛名称/训练主题
     * entry_teamA	是	Sting	关联球队（A队/发起球队）
     * game_time	是	Date	比赛/训练时间,yyyy-MM-dd HH:mm
     * continue_time	是	double	持续时间(单位：小时)
     * game_site	是	String	比赛场地
     */
    public static UrlParamsBean createTournament(String classify, String game_name, String entry_teamA, String
            game_time, String continue_time, String game_site) {
        String url = DO_MAIN_Match + "league/tournament/inte/createTournament";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("classify", classify);
        map.put("game_name", game_name);
        map.put("entry_teamA", entry_teamA);
        map.put("game_time", game_time);
        map.put("continue_time", continue_time);
        map.put("game_site", game_site);
        return new UrlParamsBean(url, map);
    }

    ///101.201.145.244:8081/center/inte/selectBound
    public static UrlParamsBean selectBound() {
        String url = DO_MAIN + "user/center/inte/selectBound";
        HashMap<String, String> map = new HashMap<String, String>();
        return new UrlParamsBean(url, map);
    }


    public static UrlParamsBean createTournament1(String classify, String game_name, String entry_teamA, String
            entry_teamB, String uniform_teamA, String game_time, String continue_time, String apply_end_time, String
                                                          game_system, String game_site, String game_cost, String value_added) {
        String url = DO_MAIN_Match + "league/tournament/inte/createTournament";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("classify", classify);
        map.put("game_name", game_name);
        map.put("entry_teamA", entry_teamA);
        map.put("entry_teamB", entry_teamB);
        map.put("uniform_teamA", uniform_teamA);
        map.put("game_time", game_time);
        map.put("continue_time", continue_time);
        map.put("apply_end_time", apply_end_time);
        map.put("game_system", game_system);
        map.put("game_site", game_site);
        map.put("game_cost", game_cost);
        map.put("value_added", value_added);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean updateMatch(String classify, String game_name, String entry_teamA, String
            entry_teamB, String uniform_teamA, String game_time, String continue_time, String apply_end_time, String
                                                    game_system, String game_site, String game_cost, String value_added) {
        String url = DO_MAIN_Match + "league/tournament/inte/updateGame";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("classify", classify);
        map.put("game_name", game_name);
        map.put("entry_teamA", entry_teamA);
        map.put("entry_teamB", entry_teamB);
        map.put("uniform_teamA", uniform_teamA);
        map.put("game_time", game_time);
        map.put("continue_time", continue_time);
        map.put("apply_end_time", apply_end_time);
        map.put("game_system", game_system);
        map.put("game_site", game_site);
        map.put("game_cost", game_cost);
        map.put("value_added", value_added);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean searchTeamAndUserAndGame(String name, String type) {
        String url = DO_MAIN_Refrech + "team/team/searchTeamAndUserAndGame";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("type", type);
        return new UrlParamsBean(url, map);
    }

    ///101.201.145.244:8085/tournament/inte/auditOrFightGame、
    public static UrlParamsBean auditOrFightGame(String game_id, String type, String comment, String cause) {
        String url = DO_MAIN_Match + "league/tournament/inte/auditOrFightGame";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("game_id", game_id);
        map.put("type", type);
        map.put("comment", comment);
        map.put("cause", cause);
        return new UrlParamsBean(url, map);
    }

    ///101.201.145.244:8085/tournament/inte/auditOrFightGame、
    public static UrlParamsBean auditOrFightGame(String game_id, String type, String comment, String cause, String uniform_teamB) {
        String url = DO_MAIN_Match + "league/tournament/inte/auditOrFightGame";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("game_id", game_id);
        map.put("type", type);
        map.put("comment", comment);
        map.put("cause", cause);
        map.put("uniform_teamB", uniform_teamB);
        return new UrlParamsBean(url, map);
    }


    //101.201.145.244:8085/gameMember/inte/participationGame
    public static UrlParamsBean participationGame(String game_id, String team_id, String join_status) {
        String url = DO_MAIN_Match + "league/gameMember/inte/participationGame";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("game_id", game_id);
        map.put("team_id", team_id);
        map.put("join_status", join_status);
        return new UrlParamsBean(url, map);
    }

    //    47.93.119.150:2498/user/fans/inte/attention
    public static UrlParamsBean attention(String focus) {
        String url = DO_MAIN + "user/fans/inte/attention";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("focus", focus);
        return new UrlParamsBean(url, map);
    }

    ///     101.201.145.244:8081/fans/inte/cancelAttention
    //    47.93.119.150:2498/user/fans/inte/cancelAttention
    public static UrlParamsBean cancelAttention(String focus) {
        String url = DO_MAIN + "user/fans/inte/cancelAttention";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("focus", focus);
        return new UrlParamsBean(url, map);
    }

    //    测试地址：47.93.119.150:2498/league/gameCollection/inte/collect
    public static UrlParamsBean collect(String game_id) {
        String url = DO_MAIN_Match + "/league/gameCollection/inte/collect";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("game_id", game_id);
        return new UrlParamsBean(url, map);
    }

    //    测试地址：47.93.119.150:2498/league/gameCollection/inte/cancelCollect
    public static UrlParamsBean cancelCollect(String game_id) {
        String url = DO_MAIN_Match + "/league/gameCollection/inte/cancelCollect";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("game_id", game_id);
        return new UrlParamsBean(url, map);
    }

    //    /101.201.145.244:8085/tournament/inte/updateGame
//    测试地址：47.93.119.150:2498/league/tournament/inte/updateGame

    /**
     * game_name	否	String	比赛名称/训练主题
     * entry_teamA	是	Sting	关联球队（A队/发起球队）
     * game_time	是	Date	比赛/训练时间,yyyy-MM-dd HH:mm
     * continue_time	是	double	持续时间(单位：小时)
     * game_site	是	String	比赛场地
     */
    public static UrlParamsBean updateGame(String classify, String game_name, String entry_teamA, String
            game_time, String continue_time, String game_site) {
        String url = DO_MAIN_Match + "league/tournament/inte/updateGame";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("classify", classify);
        map.put("game_name", game_name);
        map.put("entry_teamA", entry_teamA);
        map.put("game_time", game_time);
        map.put("continue_time", continue_time);
        map.put("game_site", game_site);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean updateGame1(String classify, String game_name, String entry_teamA, String
            game_time, String continue_time, String game_site, String game_id) {
        String url = DO_MAIN_Match + "league/tournament/inte/updateGame";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("classify", classify);
        map.put("game_name", game_name);
        map.put("entry_teamA", entry_teamA);
        map.put("game_time", game_time);
        map.put("continue_time", continue_time);
        map.put("game_site", game_site);
        map.put("game_id", game_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean updateTeamInvite(String yaoqingma, String team_id) {
        String url = DO_MAIN_Match + "team/team/inte/updateTeamInvite";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("invite", yaoqingma);
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean applyJoinTeam(String joinStr, String team_id) {
        String url = DO_MAIN_Match + "team/team/inte/applyJoinTeam";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("remark", joinStr);
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean dissolveTeam(String team_id) {

        String url = DO_MAIN_Match + "team/team/inte/dissolveTeam";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean attentionTeam(String team_id) {
        String url = DO_MAIN_Match + "team/teamFans/inte/attentionTeam";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean cancelAttentionTeam(String team_id) {
        String url = DO_MAIN_Match + "team/teamFans/inte/cancelAttentionTeam";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean cancelGame(String game_id, String cancleStr) {
        String url = DO_MAIN_Match + "league/tournament/inte/cancelGame";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("game_id", game_id);
        map.put("cause", cancleStr);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean selectManager(String team_id) {
        String url = DO_MAIN_Match + "team/team/inte/selectTeamMemberManageList";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean removeMember(String team_id, String ycUserId) {
        String url = DO_MAIN_Match + "team/team/inte/removeMember";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        map.put("user_id", ycUserId);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean updateJerseyNo(String team_id, String ycUserId, String xg_num) {

        String url = DO_MAIN_Match + "team/team/inte/updateJerseyNo";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        map.put("user_id", ycUserId);
        map.put("jersey_no", xg_num);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean auditJoin(String s, String join_id) {
        String url = DO_MAIN_Match + "team/team/inte/auditJoin";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("join_id", join_id);
        map.put("status", s);
        map.put("audit_msg", "");
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean handoverManagement(String team_id, String user_id) {
        String url = DO_MAIN_Match + "team/team/inte/handoverManagement";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        map.put("user_id", user_id);
        return new UrlParamsBean(url, map);
    }

    //    /101.201.145.244:8083/index/inte/getRecommendationUser
//    测试地址：47.93.119.150:2498/team/index/inte/getRecommendationUser
    public static UrlParamsBean getRecommendationUser(String number) {
        String url = DO_MAIN_Refrech + "/team/index/inte/getRecommendationUser";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("number", number);
        return new UrlParamsBean(url, map);
    }

    //    /101.201.145.244:8085/tournament/inte/selectCollectionGameList
//    测试地址：47.93.119.150:2498/league/tournament/inte/selectCollectionGameList
    public static UrlParamsBean selectCollectionGameList(String pageNum, String pageSize) {
        String url = DO_MAIN_Match + "/league/tournament/inte/selectCollectionGameList";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        return new UrlParamsBean(url, map);
    }

    //    获取发现首页数据（定义）
//    /101.201.145.244:8085/found/inte/index
    public static UrlParamsBean index() {
        String url = DO_MAIN_Match + "system/find/getBanner";
        HashMap<String, String> map = new HashMap<String, String>();
        return new UrlParamsBean(url, map);
    }

    //    获取赛事榜单（定义）
//    /101.201.145.244:8085/league/inte/footballerRanking
    public static UrlParamsBean footballerRanking(String league_id, String ranking_type) {
        String url = DO_MAIN_Match + "league/inte/footballerRanking";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("league_id", league_id);
        map.put("ranking_type", ranking_type);
        return new UrlParamsBean(url, map);
    }

    //   正式地址： /101.201.145.244:8083/team/getBillboard
//    测试地址：47.93.119.150:2498/team/team/getBillboard
    public static UrlParamsBean getBillboard(String type) {
        String url = DO_MAIN_Refrech + "team/team/getBillboard";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        return new UrlParamsBean(url, map);
    }


    public static UrlParamsBean honor(String team_id) {
        String url = DO_MAIN_Refrech + "team/team/web/honor";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }

    /**
     * user_id	是	String	主键，用户唯一id
     * nickname	否	String	昵称
     * portrait	否	String	头像
     * stature	否	String	身高,单位（CM）
     * weight	否	int	体重，单位（Kg）
     * sex	否	int	性别 1：男 2：女
     * birthday	否	date	出生年月
     * veteran	否	int	球龄，只可为整数
     * foot	否	int	惯用脚，1：左脚 2：右脚
     * position	否	String	擅长位置，多个位置，以”,”号隔开
     * province	否	String	省，外键，关联省数据表
     * city	否	String	市，外键，关联市数据表
     * label	否	String	个人标签，多个位置以”,”号隔开
     */
    public static UrlParamsBean modifyInfo(String user_id, String nickname, String portrait,
                                           String stature, String weight, String sex,
                                           String birthday, String veteran, String foot,
                                           String position, String province, String city, String label) {
        String url = DO_MAIN + "user/center/inte/modifyInfo";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("user_id", user_id);
        map.put("nickname", nickname);
        map.put("portrait", portrait);
        map.put("stature", stature);
        map.put("typweighte", weight);
        map.put("sex", sex);
        map.put("birthday", birthday);
        map.put("veteran", veteran);
        map.put("foot", foot);
        map.put("position", position);
        map.put("province", province);
        map.put("city", city);
        map.put("label", label);
        return new UrlParamsBean(url, map);
    }

    ///101.201.145.244:8083/team/team/getTeamPower
    //测试地址：47.93.119.150:2498/team/team/getTeamPower
    public static UrlParamsBean getTeamPower(String team_id) {
        String url = DO_MAIN_Refrech + "team/team/getTeamPower";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        return new UrlParamsBean(url, map);
    }

    ///101.201.145.244:8085/tournament/inte/selectGameList
    //测试地址：47.93.119.150:2498/league/tournament/getAboutGameList
    public static UrlParamsBean getAboutGameList() {
        String url = DO_MAIN_Match + "league/tournament/getAboutGameList";
        HashMap<String, String> map = new HashMap<String, String>();
        return new UrlParamsBean(url, map);
    }

    ///101.201.145.244:8085/tournament/inte/selectGameList
    //测试地址：47.93.119.150:2498/league/tournament/getAboutGameList
    public static UrlParamsBean getAboutGameList(String pageNum, String pageSize, String name, String game_time,
                                                 String province, String city, String area, String game_system) {
        String url = DO_MAIN_Match + "league/tournament/getAboutGameList";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        map.put("name", name);
        map.put("game_time", game_time);
        map.put("province", province);
        map.put("city", city);
        map.put("area", area);
        map.put("game_system", game_system);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean updatePlace(String team_id, String ycUserId, String returnStr) {
        String url = DO_MAIN_Match + "team/team/inte/updatePlace";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        map.put("user_id", ycUserId);
        map.put("place", returnStr);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean select(String id, String s, String s1) {
        String url = DO_MAIN_Match + "league/league/join/select";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("league_id", id);
        map.put("pageNum", s1);
        map.put("pageSize", s);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean settingIdentity(String team_id, String ycUserId, String sf_str, String qx_str) {
        String url = DO_MAIN_Match + "team/team/inte/settingIdentity";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        map.put("user_id", ycUserId);
        map.put("identity", sf_str);
        map.put("jurisdiction", qx_str);
        return new UrlParamsBean(url, map);
    }

    //    /101.201.145.244:8085/gameGrand/inte/enterGrand
//    测试地址：47.93.119.150:2498/league/gameGrand/inte/enterGrand
    public static UrlParamsBean enterGrand(String data_json) {
        String url = DO_MAIN_Match + "league/gameGrand/inte/enterGrand";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("data_json", data_json);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getMessageList() {
        String url = DO_MAIN_Match + "team/index/inte/getMessageList";
        HashMap<String, String> map = new HashMap<String, String>();
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean getMessage(String type, String typeid, String s, String s1) {
        String url = DO_MAIN_Match + "team/index/inte/getMessage";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("message_type", type);
        map.put("type_id", typeid);
        map.put("pageNum", s);
        map.put("pageSize", s1);
        return new UrlParamsBean(url, map);
    }

    /**
     * /101.201.145.244:8081/center/inte/bound
     * 测试地址：47.93.119.150:2498/user/center/inte/bound
     */
    public static UrlParamsBean bound(String identifier, String type) {
        String url = DO_MAIN + "user/center/inte/bound";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        map.put("identifier", identifier);
        return new UrlParamsBean(url, map);
    }

    /**
     * ///101.201.145.244:8081/center/inte/unwrap
     * //测试地址：47.93.119.150:2498/user/center/inte/unwrap
     */
    public static UrlParamsBean unwrap(String identifier, String type) {
        String url = DO_MAIN + "user/center/inte/unwrap";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        map.put("identifier", identifier);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean applyJoinTeamByInvite(String joinStr, String team_id) {
        String url = DO_MAIN + "team/team/inte/applyJoinTeamByInvite";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("team_id", team_id);
        map.put("invite", joinStr);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean auditInvitation(String yq_str, String s) {
        String url = DO_MAIN + "team/team/inte/auditInvitation";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("invitation_id", yq_str);
        map.put("audit_status", s);
        return new UrlParamsBean(url, map);
    }

    public static UrlParamsBean selectInfo() {
        String url = DO_MAIN + "user/center/inte/selectInfo";
        HashMap<String, String> map = new HashMap<String, String>();
        return new UrlParamsBean(url, map);
    }
}
