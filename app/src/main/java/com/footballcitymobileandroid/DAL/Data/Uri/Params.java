package com.footballcitymobileandroid.DAL.Data.Uri;


import com.footballcitymobileandroid.BLL.Util.Cache.FileCache.FileUtils;

/**
 * Created by smartlab on 16/5/9.
 * 应用地址信息
 */
public class Params {
    /**
     * 远程测试服务地址
     */
    public static final String Base="http://114.55.173.137:8080/FootBallCity";
    /**
     * 测试地址
     */
  //  public static final String Base="http://192.168.31.102:8080/FootBallCity_v2";
  //  public static final String Base="http://192.168.31.154:8080";
//    public static final String Base="http://192.168.1.114:8080";
   // public static final String Base="http://192.168.31.41:8080/FootBallCity";
    public static final String Common=Base+"/web/rs/common/";//FootBallCity_v2/
    public static final String Club=Base+"/web/rs/club/";
    public static final String sport = Base + "/web/rs/sport/";
    public static final String join = Base + "/web/rs/Join/";
    public static final String arena1 = Base + "/web/rs/arena/";
    public static final String arena2 = Base + "/web/rs/arena/";
    public static final String arena3 = Base + "/web/rs/arena/";


    /**
     * common
     * 用户注册
     */
    public static final String fc_userReg=Common+"fc_userReg";
    /**
     * 获取手机验证码
     */
    public static final String fc_get_usercode=Common+"fc_get_usercode";
    /**
     * 用户登入
     */
    public static final String fc_userLogin=Common+"fc_userLogin";
    /**
     *用户退出
     */
    public static final String fc_userExit=Common+"fc_userExit";
    /**
     * 查询自己详细信息
     */
    public static final String fc_queryMyself=Common+"fc_queryMyself";
    /**
     * 修改自己详细信息
     */
    public static final String fc_modifyMyself=Common+"fc_modifyMyself";
    /**
     * 修改自己密码
     */
    public static final String fc_modifyPwd=Common+"fc_modifyPwd";
    /**
     * 忘记密码
     */
    public static final String fc_forgetPwd=Common+"fc_forgetPwd";
    /**
     * 修改自己手机号
     */
    public static final String fc_modifyPhone=Common+"fc_modifyPhone";
    /**
     * 获取配置数据
     */
    public static final String fc_getConfig=Common+"fc_getConfig";
    /**
     * 10.10 查询球员信息(条件查询)（fc_queryPlayers）（登入：无，系统权限：无，业务权限：无）
     */
    public static final String fc_queryPlayers=Common+"fc_queryPlayers";
    /**
     * 11.查询球员信息（fc_queryPlayer）（登入：无，系统权限：无，业务权限：无）
     */
    public static final String fc_queryPlayer=Common+"fc_queryPlayer";
    /**
     * 12.查询球员比赛信息（fc_queryPlayerCurRecord）（登入：无，系统权限：无，业务权限：无）
     */
    public static final String fc_queryPlayerCurRecord=Common+"fc_queryPlayerCurRecord";
    /**
     * 13.查询球员比赛信息[分页]（fc_queryPlayerRecords）（登入：无，系统权限：无，业务权限：无）
     */
    public static final String fc_queryPlayerRecords=Common+"fc_queryPlayerRecords";

    /**
     * club
     * 创建俱乐部
     */
    public static final String fc_createClub=Club+"fc_createClub";

    /**
     * 修改俱乐部
     */
    public static final String fc_modifyClub = Club + "fc_modifyClub";
    /**
     * 改变俱乐部领队
     */
    public static final String fc_changeLeader = Club + "fc_changeLeader";

    /**
     * 获取用户俱乐部列表
     */
    public static final String fc_getClubList = Club + "fc_getClubList";
    /**
     * 解散俱乐部
     */
    public static final String fc_fireClub = Club + "fc_fireClub";
    /**
     * 查询俱乐部
     */
    public static final String fc_queryClubs = Club + "fc_queryClubs";
    /**
     * 获取俱乐部详细信息
     */
    public static final String fc_queryClubDetail = Club + "fc_queryClubDetail";
    /**
     * 获取俱乐部比赛记录
     */
    public static final String fc_queryClubCurRecord = Club + "fc_queryClubCurRecord";
    /**
     * 获取俱乐部比赛记录（分页）
     */
    public static final String fc_queryClubRecords = Club + "fc_queryClubRecords";
    /**
     * 分配球员场上位置
     */
    public static final String fc_setPosition = Club + "fc_setPosition";
    /**
     * 取消球员场上位置
     */
    public static final String fc_fireClubMemb = Club + "fc_fireClubMemb";
    /**
     * 检查俱乐部球员信息
     */
    public static final String fc_checkClubMemb = Club + "fc_checkClubMemb";
    /**
     * 退出俱乐部
     */

    public static final String fc_exitClub = Club + "fc_exitClub";


    /**
     * sport
     * 添加比赛信息
     */
    public static final String fc_addClubSport = sport + "fc_addClubSport";
    /**
     * 删除比赛信息
     */
    public static final String fc_delClubSport = sport + "fc_delClubSport";
    /**
     * 查看比赛信息列表
     */
    public static final String fc_checkSportDetail = sport + "fc_checkSportDetail";
    /**
     * 录入比赛结果
     */
    public static final String fc_editScore = sport + "fc_editScore";
    /**
     * 签到
     */
    public static final String fc_sportSign = sport + "fc_sportSign";
    /**
     * 查看签到人员
     */
    public static final String fc_checkSportMemb = sport + "fc_checkSportMemb";
    /**
     * 布置比赛人员
     */
    public static final String fc_deploySport = sport + "fc_deploySport";

    /**
     * 查询App最新版本
     */
    public static final String fc_latestVersion = Common + "fc_latestVersion";

    /**
     * join
     * 球员申请加盟
     */
    public static final String fc_createJoinApply = join + "fc_createJoinApply";

    /**
     * 俱乐部邀请球员加盟
     */
    public static final String fc_createInvitation = join + "fc_createInvitation";

    /**
     * 俱乐部领队处理加盟信息
     */
    public static final String fc_dealJoinApply = join + "fc_dealJoinApply";

    /**
     * 球员处理受邀请信息
     */
    public static final String fc_dealInvitation = join + "fc_dealInvitation";

    /**
     * 球员查询被邀请记录
     */
    public static final String fc_checkInvitation = join + "fc_checkInvitation";

    /**
     * 俱乐部查询申请加盟记录
     */
    public static final String fc_checkJoinApply = join + "fc_checkJoinApply";


    /**
     * arena1
     * 1.查询竞技场（fc_queryArena）（无需登录，系统权限：无，业务权限：无）
     */
    public static final String fc_queryArena = arena1 + "fc_queryArena";
    /**
     * 2.获取匹配条件（fc_getMatchingCondition）（需登录，需要是领队，业务权限：需要有一家俱乐部）
     */
    public static final String fc_getMatchingCondition = arena1 + "fc_getMatchingCondition";
    /**
     * 3得到能匹配的俱乐部（fc_getMatchingClub）（无需登录，系统权限：无，业务权限：无）
     */
    public static final String fc_getMatchingClub = arena1 + "fc_getMatchingClub";
    /**
     * 4.发送匹配请求（fc_sendMatchingMsg）（需登录，系统权限：无，业务权限：无）
     */
    public static final String fc_sendMatchingMsg = arena1 + "fc_sendMatchingMsg";
    /**
     * 5查看匹配信息（fc_checkMatchingMsg） （需登录，系统权限：无，业务权限：无）
     */
    public static final String fc_checkMatchingMsg = arena1 + "fc_checkMatchingMsg";
    /**
     * 6.查看竞技挑战赛（fc_checkArenaMatch）（需登录，系统权限：无，业务权限：无）
     * '挑战赛状态：1-待开始；2-退赛中；3-已开始；4-强制退赛结束；5-正常退赛结束；6-正常比赛结束',
     */
    public static final String fc_checkArenaMatch = arena1 + "fc_checkArenaMatch";
    /**
     * 7.处理匹配请求（fc_dealMatchingMsg）（需登录，系统权限：无，业务权限：无）
     */
    public static final String fc_dealMatchingMsg = arena1 + "fc_dealMatchingMsg";


    /**
     * arena2
     * 6.8.1/2 签到(fc_arenaSign)（登陆：有 权限：无 业务角色：俱乐部成员）
     */
    public static final String fc_arenaSign = arena2 + "fc_arenaSign";
    /**
     * 6.8.3 查询竞技场签到成员(fc_checkArenaMatchMemb)（登陆：有 权限：无 业务角色：俱乐部成员）
     */
    public static final String fc_checkArenaMatchMemb = arena2 + "fc_checkArenaMatchMemb";
    /**
     * 6.9.1 布置竞技场比赛人员(fc_deployArenaMatch)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public static final String fc_deployArenaMatch = arena2 + "fc_deployArenaMatch";
    /**
     * 6.10.1 退赛申请(fc_quitApply)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public static final String fc_quitApply = arena2 + "fc_quitApply";
    /**
     * 6.10.2/3 处理退赛申请(fc_dealQuitApply)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public static final String fc_dealQuitApply = arena2 + "fc_dealQuitApply";
    /**
     * 6.10.4 强行退赛申请(fc_forcedQuit)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public static final String fc_forcedQuit = arena2 + "fc_forcedQuit";



    /**
     * arena3
     * 14、编辑进球数（fc_editGoals）
     */
    public static final String fc_editGoals = arena3 + "fc_editGoals";
    /**
     * 15、提交提名名单（fc_gradeMatchMemb）
     */
    public static final String fc_gradeMatchMemb = arena3 + "fc_checkArenaClubRankings";
    /**
     * 16、检查提名名单（fc_checkNominationMemb）
     */
    public static final String fc_checkNominationMemb = arena3 + "fc_checkArenaClubRankings";
    /**
     * 17，投票（fc_voteNomiMemb）
     */
    public static final String fc_voteNomiMemb = arena3 + "fc_voteNomiMemb";
    /**
     * 18、检查竞技场选手排行(fc_checkArenaClubRankings)
     */
    public static final String fc_checkArenaPlayerRankings = arena3 + "fc_checkArenaPlayerRankings";
    /**
     * 19、检查竞技场俱乐部排行(fc_checkArenaClubRankings)
     */
    public static final String fc_checkArenaClubRankings = arena3 + "fc_checkArenaClubRankings";



    /**
     * 数据文件保存路径
     */
    public static final String DATA_PATH = FileUtils.getRootPath() + "/Football_Drive/";//应用程序文件保存路径

    /**
     * 文件下载路径
     */
    public static final String DOWN_LOAD = "/Drive/download";
}
