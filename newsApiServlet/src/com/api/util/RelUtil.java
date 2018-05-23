/**
 * @author ChenDu GOGO  Administrator
 *	ʱ��   2012 ����2:48:50
 *  ����com.gogofood.bean.util
            ������GoGoCity
 */
package com.api.util;

/**
 * @author Administrator
 * 
 */
public class RelUtil {
	// 微信支付结果广播
	public static String BROADCAST_ACTION_WXPAY = "com.gogofood.action.wxpay.result";

	public static String UPDATE_TYPE_ZIP = "gogo.index.update.zip";
	public static String UPDATE_TYPE_SUBSCRIBE = "gogo.index.update.subscribe";
	public static String UPDATE_TYPE_APP = "gogo.index.update.app";
	public static String UPDATE_TYPE_FRAME = "gogo.index.update.frame";

	public static String SELF_DOWN = "down.self";
	public static String SELF_UP = "up.self";

	public static String SELF_DEL = "self:del";
	public static String SELF_ADD = "self:add";
	public static String SELF_EDIT = "self:edit";
	public static String SELF_SET_DEFAULT = "self:setDefault";
	// 选择街道
	public static String SELF_SELECT_STREET = "address.selectStreet";
	// 搜索街道
	public static String SELF_SEARCH_STREET = "address.searchStreet";

	public static String SELF_UPLOAD = "self:upload";

	// 更新
	public static String APP_UPDATE = "self:update";

	// 登出
	public static String USER_LOGINOUT = "self:loginout";

	// 创建订单
	public static String ORDER_CREATE_ORDER = "order.createOrder";
	// 订单
	public static String ORDER_CREATE_PAY = "order.createPay";
	// 订单结算
	public static String ORDER_GO_BALANCE = "order.gobalance";

	// 购物车
	public static String SELF_SHOPPINGCART = "self:shoppingcart";
	// 上传购物车
	public static String SELF_UPDLOAD_CART = "self:uploadcart";

	// 登录
	public static String USER_LOGIN = "users.authenticate";
	// 第三方登录
	public static String USER_LOGIN_THIRD = "self:checkotherlogin";
	// 获取地址
	public static String ORDER_GET_ADDRESS = "order.address";
	// 得到token
	public static String USER_GET_TOKEN = "user.getToken";
	// 短信验证
	public static String USER_VERFY_PHONE = "user.verifyPhone";
	public static String USER_VERFY_PHONE_LOGIN = "1";
	public static String USER_VERFY_PHONE_FIND_PASSWD = "2";
	public static String USER_VERFY_PHONE_CHANGE_PHONE = "3";

	// 注册
	public static String USER_REGIST = "user.register";
	// 定位
	public static String LBS_GET_LBG = "LBS.lbs";
	// 首页
	public static String HOME_LUNCH = "home.lunch";
	// 找回密码
	public static String SELF_FINDPWD = "self:findPwd";
	// 同意协议
	public static String SELF_AGREE = "self:terms";

	// 最新活动
	public static String HOME_NEWS = "home.activity";
	// 首页
	public static String HOME_TOP = "home.chefList";
	// 我的订单界面
	public static String ORDER_MYORDER = "order.myOrder";
	// 我的订单查询
	public static String ORDER_SUCCEED_ORDER = "order.succeedOrder";

	// 取消订单原因
	public static String ORDER_APPLY = "self:apply";
	// 再卖一次
	public static String ORDER_BUYONE = "self:buyOne";
	// 去付款
	public static String ORDER_PAY = "self:goPay";
	// 取消订单
	public static String ORDER_CANCEL = "self:cancel";
	// 去评论
	public static String ORDER_COMMENT = "self:comment";
	// 提醒评论
	public static String ORDER_REMINDCOMMENT = "remindComment";
	// 去退款
	public static String ORDER_REFUND = "self:refund";
	// 还想评价订单
	public static String ORDER_WANTCOMMENT = "self:wantcomment";
	// 订单详情
	public static String ORDER_ORDERINFO = "self:orderInfo";

	// 个人中心
	public static String USER_HOME = "user.home";
	// APP介绍
	public static String SETTING_INTRO = "setting.intro";
	// 个人反馈
	public static String USER_FEEDBACK = "setting.feedback";
	// 关于
	public static String APP_ABOUT = "setting.about";

	// 访客首页
	public static String USER_INFO = "user.info";
	// 食品详情
	public static String FOOD_DETAIL_INFO = "details.foodInfo";
	// 食品收藏
	public static String FOOD_FAVORITES = "details.favorites";

	// 编辑个人信息
	public static String USER_EDIT_USER_INFO = "user.editUserInfo";
	// 饭丝列表
	public static String USER_FANS = "user.fans";
	// 关注列表
	public static String USER_FOLLOWER = "user.follower";
	// 添加朋友
	public static String USER_FRIEND = "user.addFriend";
	// GOGO的朋友
	public static String USER_GOGOFRIEND = "user.gogoFriend";

	// 食物详情
	public static String REDIRECT_FOODINFO = "redirect.foodInfo";
	// 用户详情-厨师|redirect.userInfo
	public static String REDIRECT_USERINFO = "redirect.userInfo";
	// 分享|redirect.share
	public static String REDIRECT_SHARE = "redirect.share";
	// 排行|redirect.top
	public static String REDIRECT_TOP = "redirect.top";
	// 我的订单|redirect.myOrder
	public static String REDIRECT_MYORDER = "redirect.myOrder";
	// 主页|redirect.home
	public static String REDIRECT_HOME = "redirect.home";

	// 城市分站定位
	public static String SELF_LOCATION = "self:location";

	// 用户收藏
	public static String USER_FAVORITE = "user.favorite";

	// 成功分享通知服务器
	public static String SHARE_SUCCESS = "self:successShare";

	// 加入餐饮服务
	public static String USER_JOINMOTHER = "user.joinMother";

	// 我的订单
	public static String USER_MYORDER = "user.myOrder";
	// 我的点评
	public static String USER_MYCOMMENT = "user.myComment";
	// 消息
	public static String USER_MESSAGE = "user.message";
	// 设置
	public static String SETTING_INDEX = "setting.index";
	// 加入餐饮服务html5页面
	public static String HTML5_JOINTERMS = "html5.joinTerms";
	// html5 错误页面
	public static String HTML5_ERROR = "html5.error";

	// 更改手机号
	public static String USERS_CHANGEMOBILE = "users.changeMobile";
	// 退款详情
	public static String ORDER_REFUNDDETAIL = "order.refundDetail";
	// 更改密码
	public static String USERS_CHANGEPWD = "users.changePWD";
	// 管理地址
	public static String ORDER_ADDRESS = "order.address";

	// 关注
	public static String USER_FOLLOW = "user.follow";
	// 用户点评
	public static String USER_REMARK = "user.remark";
	// 大妈拿手菜
	public static String USER_SPECIALFOOD = "user.specialFood";
	// 评价留言
	public static String USER_CHEFREMARK = "user.chefRemark";

}
