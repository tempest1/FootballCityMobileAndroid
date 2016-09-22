package com.footballcitymobileandroid.BLL.Interface;

import com.footballcitymobileandroid.Entity.Base.BaseEntity;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Club;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubList;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.club.ClubRecord;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Clubs;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Send;
import com.footballcitymobileandroid.Entity.ClubEntity.club.Wrong;
import com.footballcitymobileandroid.Entity.ClubEntity.join.Invitations;
import com.footballcitymobileandroid.Entity.ClubEntity.join.JoinApplys;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.DeployDetail;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.MatchMemb;
import com.footballcitymobileandroid.Entity.ClubEntity.sport.SportDetail;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.AranaMatchs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.Condition;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.MatchingInfo;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena1.updateArena;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2.AranaMatchMembs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena2.DeployInfos;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.ClubRankings;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.GradeList;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.NomiMembs;
import com.footballcitymobileandroid.Entity.GymkhanaEntity.Arena3.PlayerRankings;
import com.footballcitymobileandroid.Entity.MeEntity.App;
import com.footballcitymobileandroid.Entity.MeEntity.PlayerRecord;
import com.footballcitymobileandroid.Entity.MeEntity.Players;
import com.footballcitymobileandroid.Entity.MeEntity.userInfo;

import java.util.List;

/**
 * 应用数据调用接口
 * Created by smartlab on 16/5/10.
 */
public interface AppAction {
    /**
     * 用户注册
     */
    public void fc_userReg(String phoneNumb, String passWord, String c_passWord, String param_Value, String url, ActionCallBackListener<BaseEntity<userInfo>> listener);

    /**
     * 获取手机验证码
     */
    public void fc_get_usercode(final String phoneNumb,final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener);

    /**
     * 用户登录
     */
    public void fc_userLogin(final String phoneNumb,String passWord,final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener);

    /**
     * 用户退出
     */
    public void fc_userExit(final String phoneNumb, String passWord, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 查询自己详细信息
     */
    public void fc_queryMyself(final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener);

    /**
     * 修改自己详细信息
     */
    public void fc_modifyMyself(final String name,final String namevalue,final int[] positionvalue,final String fieldID,final int[] atytimevalue ,final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener);
    public void fc_modifyMyselfs(final String name,final Object namevalue,final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener);


    /**
     * 修改自己密码
     */
    public void fc_modifyPwd(final String newPwd,final String code,final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener);

    /**
     * 忘记密码
     */
    public void fc_forgetPwd(final String phone,final String newPwd, final String code,final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener);

    /**
     * 修改自己手机号
     */
    public void fc_modifyPhone(final String code1,final String newPhoneNumb,final String code2,final String passWord,final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener);

    /**
     * 获取配置数据（fc_getConfig）（登入：有，系统权限：无，业务权限：无）
     */
    public void fc_getConfig(final String url,final ActionCallBackListener<BaseEntity<userInfo>> listener);

    /**
     * 10.10 查询球员信息(条件查询)（fc_queryPlayers）（登入：无，系统权限：无，业务权限：无）
     */
    public void fc_queryPlayers(final String type, final Object value, final String url,final ActionCallBackListener<BaseEntity<Players>> listener);
    /**
     * 11.查询球员信息（fc_queryPlayer）（登入：无，系统权限：无，业务权限：无）
     */
    public void fc_queryPlayer(final String playerIDvalue,final String url,final ActionCallBackListener<BaseEntity<Players>> listener);
    /**
     * 12.查询球员比赛信息（fc_queryPlayerCurRecord）（登入：无，系统权限：无，业务权限：无）
     */
    public void fc_queryPlayerCurRecord(final String playerIDvalue, final String url,final ActionCallBackListener<BaseEntity<PlayerRecord>> listener);
    /**
     * 13.查询球员比赛信息[分页]（fc_queryPlayerRecords）（登入：无，系统权限：无，业务权限：无）
     */
    public void fc_queryPlayerRecords(final String page,final String playerIDvalue, final String url,final ActionCallBackListener<BaseEntity<PlayerRecord>> listener);
    /**
     * 1.查询App最新版本 (fc_latestVersion) （登陆：无	权限：无	业务角色：无）
     */
    public void fc_latestVersion(final String url,final ActionCallBackListener<BaseEntity<App>> listener);



    //sport

    /**
     * 添加比赛信息 (fc_addClubSport) （登陆：有	权限：无	业务角色：俱乐部领队）
     */
    public void fc_addClubSport(final String clubID, final String startTime, final String endTime, final String visitingTeam, final String joinNum, final String sportField, final String sportState, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 删除比赛信息 (fc_delClubSport) （登陆：有	权限：无	业务角色：俱乐部领队）
     */
    public void fc_delClubSport(final String clubID, final String sportID, final String url, ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 查看比赛信息列表 (fc_checkSportDetail) （登陆：有	权限：无	业务角色：俱乐部成员）
     */
    public void fc_checkSportDetail(final String clubID, final String sportState, final String page, final String url, ActionCallBackListener<BaseEntity<SportDetail>> listener);

    /**
     * 录入比赛结果 (fc_editScore) （登陆：有	权限：无	业务角色：俱乐部领队）
     */
    public void fc_editScore(final String clubID, final String sportID, final String homeScore, final String visitingScore, final String url, ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 签到 (fc_sportSign) （登陆：有	权限：无	业务角色：俱乐部成员）
     */
    public void fc_sportSign(final String clubID, final String sportID, final String sign, final String url, ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 查看签到人员 (fc_checkSportMemb) （登陆：有	权限：无	业务角色：俱乐部成员）
     */
    public void fc_checkSportMemb(final String clubID, final String sportID, final String url, ActionCallBackListener<BaseEntity<MatchMemb>> listener);

    /**
     * 布置比赛人员	 (fc_deploySport) （登陆：有	权限：无	业务角色：俱乐部领队）
     */
    public void fc_deploySport(final String clubID, final String sportID, final List<DeployDetail> deployDetail, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);


    /**
     * join
     * 球员申请加盟（fc_createJoinApply）（需要登录，系统权限：无，业务权限：现阶段无）
     */
    public void fc_createJoinApply( final String receiver, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 俱乐部邀请球员加盟（fc_createInvitation）（需要登录，需要是领队，业务权限：现阶段无
     */
    public void fc_createInvitation( final String receiver, final String play, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 俱乐部领队处理加盟信息（fc_dealJoinApply）（需要登录，需要是领队，业务权限：现阶段无）
     */
    public void fc_dealJoinApply( final String play, final String receiver, final String dealResult, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 球员处理受邀请信息（fc_dealInvitation）（需要登录，业务权限：现阶段无）
     */
    public void fc_dealInvitation( final String receiver, final String dealResult, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 球员查询被邀请记录（fc_checkInvitation）（需要登录，系统权限：无，业务权限：现阶段无）
     */
    public void fc_checkInvitation( final String page, final String url, final ActionCallBackListener<BaseEntity<Invitations>> listener);

    /**
     * 俱乐部查询申请加盟记录（fc_checkJoinApply）（需要登录，系统权限：领队，业务权限：现阶段无）
     */
    public void fc_checkJoinApply(final String receiver, final String page, final String url, final ActionCallBackListener<BaseEntity<JoinApplys>> listener);
//final String sender,

    //join

    /**
     * 1、创建俱乐部（fc_createClub）（登入：有，系统权限：无，业务权限：无）
     */
    public void fc_createClub(final String logo, final String clubName, final String cityID, final String createTime, final String atyFieldID, final int[] atyTime,final String welfare, final String url, final ActionCallBackListener<BaseEntity<Club>> listener);

    /**
     * 2、修改俱乐部（fc_modifyClub）（登入：有，业务权限：领队)
     * "modifyType": "logo",//clubName,createTime,clubWelfare  //默认图标只是其中一项
     */
    public void fc_modifyClub(final String clubID,final String type,final Object typevalue,  final String url, final ActionCallBackListener<BaseEntity<Wrong>> listener);

    /**
     * 3、改变俱乐部领队（fc_changeLeader）（登入：有，业务权限：领队）
     */
    public void fc_changeLeader(final String clubID, final String leaderID, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 4.获取用户俱乐部列表(fc_getClubList)
     * "msg":"leader/user"; //final String msg,
     */
    public void fc_getClubList(final String url, final ActionCallBackListener<BaseEntity<ClubList>> listener);

    /**
     * 5、解散俱乐部（fc_fireClub）
     */
    public void fc_fireClub(final String clubID, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 6、fc_queryClubs待完善
     * order:"prev/next",//表示升序或降序
     * condition:条件对象，当为null时，则是查找所有clubs
     * condition包括：
     * {key：俱乐部名称；value：}
     * {key：年龄；value：}
     * {key：活动时间；value：}
     * {key:aty_Field;value:}
     *
     */
    public void fc_queryClubs(final String page, final String clubName, final String age, final int[] aty_Time, final String aty_Field, final String url, final ActionCallBackListener<BaseEntity<Clubs>> listener);

    /**
     * 7、获取俱乐部详细信息（fc_queryClubDetail）
     */
    public void fc_queryClubDetail(final String clubID, final String url, final ActionCallBackListener<BaseEntity<Club>> listener);

    /**
     * 8、获取俱乐部比赛记录（fc_queryClubCurRecord）
     *
     */
    public void fc_queryClubRecord(final String clubID, final String url, final ActionCallBackListener<BaseEntity<ClubRecord>> listener);

    /**
     * 9、获取俱乐部比赛记录（分页）（fc_queryClubRecords）
     * "page":1
     */
    public void fc_queryClubRecords(final String clubID, final String page, final String url, final ActionCallBackListener<BaseEntity<ClubRecord>> listener);

    /**
     * 10、分配球员场上位置（fc_setPosition)  针对一个球员进行一次操作,因为人数未定故不可一次操作所有//final String clubMembID, final String positionID
     */
    public void fc_setPosition(final String clubID, final List<Send> sends, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 11、解雇俱乐部成员（fc_fireClubMemb）
     */
    public void fc_fireClubMemb(final String clubID, final String clubMembID, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 12、检查俱乐部球员信息（fc_checkClubMemb）
     */
    public void fc_checkClubMemb(final String clubID, final String url, final ActionCallBackListener<BaseEntity<ClubMemb>> listener);

    /**
     * 13、退出俱乐部（fc_exitClub）
     */
    public void fc_exitClub(final String clubID, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);


    //arena1


    /**
     * 1.查询竞技场（fc_queryArena）（无需登录，系统权限：无，业务权限：无）
     */
    public void fc_queryArena(final String cityID, final String arenaID, final String seasonID, final String updateTag, final String url, final ActionCallBackListener<BaseEntity<updateArena>> listener);

    /**
     * 2.获取匹配条件（fc_getMatchingCondition）（需登录，需要是领队，业务权限：需要有一家俱乐部）
     */
    public void fc_getMatchingCondition(final String url, final ActionCallBackListener<BaseEntity<Condition>> listener);

    /**
     * 3得到能匹配的俱乐部（fc_getMatchingClub）（无需登录，系统权限：无，业务权限：无）
     */
    public void fc_getMatchingClub(final String page, final String fieldID, final String matchingDate, final String url, final ActionCallBackListener<BaseEntity<Clubs>> listener);

    /**
     * 4.发送匹配请求（fc_sendMatchingMsg）（需登录，系统权限：无，业务权限：无）
     *
     */
    public void fc_sendMatchingMsg(final String sender, final String season_id, final String matchingDate, final String matchingTime, final String matchRule, final String costModeKey, final List<String> receiver, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 5查看匹配信息（fc_checkMatchingMsg） （需登录，系统权限：无，业务权限：无）
     */
    public void fc_checkMatchingMsg(final String getType, final String page, final String clubID, final String url, final ActionCallBackListener<BaseEntity<MatchingInfo>> listener);

    /**
     * 6.查看竞技挑战赛（fc_checkArenaMatch）（需登录，系统权限：无，业务权限：无）
     * '挑战赛状态：1-待开始；2-退赛中；3-已开始；4-强制退赛结束；5-正常退赛结束；6-正常比赛结束',
     *
     */
    public void fc_checkArenaMatch(final String arenaMatchState, final String page, final String clubID, final String url, final ActionCallBackListener<BaseEntity<AranaMatchs>> listener);

    /**
     * 7.处理匹配请求（fc_dealMatchingMsg）（需登录，系统权限：无，业务权限：无）
     */
    public void fc_dealMatchingMsg(final String arenaMatchID, final String clubID, final String signIn, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);


    /**
     * arena2
     * 6.8.1/2 签到(fc_arenaSign)（登陆：有 权限：无 业务角色：俱乐部成员）
     */
    public void fc_arenaSign(final String arenaMatchID, final String clubID, final String signIn, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 6.8.3 查询竞技场签到成员(fc_checkArenaMatchMemb)（登陆：有 权限：无 业务角色：俱乐部成员）
     */
    public void fc_checkArenaMatchMemb(final String arenaMatchID, final String clubID, final String url, final ActionCallBackListener<BaseEntity<AranaMatchMembs>> listener);

    /**
     * 6.9.1 布置竞技场比赛人员(fc_deployArenaMatch)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public void fc_deployArenaMatch(final String arenaMatchID, final String clubID,  final List<DeployInfos> deployInfosList,final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 6.10.1 退赛申请(fc_quitApply)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public void fc_quitApply(final String arenaMatchID, final String clubID, final String applyDesc, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 6.10.2/3 处理退赛申请(fc_dealQuitApply)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public void fc_dealQuitApply(final String arenaMatchID, final String clubID, final String dealResult, final String applyDesc, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 6.10.4 强行退赛申请(fc_forcedQuit)（登陆：有 权限：无 业务角色：俱乐部领队）
     */
    public void fc_forcedQuit(final String arenaMatchID, final String clubID, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);


    /**
     * arena3
     */
    /**
     * 14、编辑进球数（fc_editGoals）
     */
    public void fc_editGoals(final String arenaMatchID, final String clubID, final String goals, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 15、提交提名名单（fc_gradeMatchMemb）
     */
    public void fc_gradeMatchMemb(final String arenaMatchID, final String clubID, final List<GradeList> gradeList, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 16、检查提名名单（fc_checkNominationMemb）
     */
    public void fc_checkNominationMemb(final String arenaMatchID, final String clubID, final String url, final ActionCallBackListener<BaseEntity<NomiMembs>> listener);

    /**
     * 17，投票（fc_voteNomiMemb）
     */
    public void fc_voteNomiMemb(final String arenaMatchID, final String clubID, final String voteTo, final String url, final ActionCallBackListener<BaseEntity<Void>> listener);

    /**
     * 18、检查竞技场选手排行(fc_checkArenaPlayerRankings)
     */
    public void fc_checkArenaPlayerRankings(final String seasonID, final String url, final ActionCallBackListener<BaseEntity<PlayerRankings>> listener);


    /**
     * 19、检查竞技场俱乐部排行(fc_checkArenaClubRankings)
     */
    public void fc_checkArenaClubRankings(final String seasonID, final String url, final ActionCallBackListener<BaseEntity<ClubRankings>> listener);



}
