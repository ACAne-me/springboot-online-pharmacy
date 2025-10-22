<template>
	<div class="main-containers">
		<div class="body-containers" :style='{"minHeight":"100vh","padding":"120px 0 0","margin":"0","position":"relative","background":"#ffffff"}'>
		<div class="top-container" :style='{"padding":"0 7%","top":"0","alignItems":"center","left":"0","background":"#F9F9F9","display":"flex","width":"100%","fontSize":"14px","position":"fixed","justifyContent":"flex-start","height":"60px","zIndex":"1002"}'>
			<!-- info -->
			<div :style='{"padding":"0 0 0 7%","top":"60px","alignItems":"center","left":"0","background":"#000","display":"flex","width":"37%","position":"absolute","justifyContent":"flex-start","height":"60px"}'>
			  <el-image :style='{"width":"49px","objectFit":"cover","borderRadius":"100%","float":"left","height":"49px"}' src="http://codegen.caihongy.cn/20231117/63f04864b623463587bbb553ebcc3d08.png" fit="cover" />
			  <span :style='{"padding":"0 0 0 12px","lineHeight":"44px","fontSize":"18px","color":"#fff","float":"left"}'>基于Spring Boot的网上药房</span>
			</div>
			<!-- weather -->
			<div class="weather" :style='{"padding":"0 20px","alignItems":"center","display":"flex","width":"250px","position":"absolute","right":"calc(7% + 360px)","justifyContent":"center","order":"2"}'>
				<div :style='{"padding":"0 4px","fontSize":"16px","lineHeight":"34px","color":"#333"}'>{{weather.city}}</div>
				<div :style='{"padding":"0 4px","fontSize":"16px","lineHeight":"34px","color":"#333"}'>{{weather.tem}}°</div>
				<div :style='{"padding":"0 4px","fontSize":"16px","lineHeight":"34px","color":"#333"}'>{{weather.wea}}</div>
				<div :style='{"padding":"0 4px","fontSize":"16px","lineHeight":"34px","color":"#333"}'>{{weather.win}}</div>
				<div :style='{"padding":"0 4px","fontSize":"16px","lineHeight":"34px","color":"#333"}'>{{weather.win_speed}}</div>
			</div>
			
			<div v-if="false" :style='{"color":"#666","margin":"0 10px","fontSize":"14px"}'>0753-1234567</div>
			<el-button v-if="Token" class="btn-shop" @click.native="goMenu('/index/cart')">
				<span class="icon iconfont icon-wuliu8" :style='{"color":"inherit","fontSize":"16px"}'></span>
				购物车
			</el-button>
			<el-button v-if="Token" class="btn-service" @click.native="goChat">
				<span class="icon iconfont icon-touxiang09" :style='{"color":"inherit","fontSize":"16px"}'></span>
					客服中心
			</el-button>
			
			<div id="search" class="search" :style='{"margin":"0 10px","flexWrap":"wrap","background":"#fff","display":"none","height":"auto"}'>
				<div :style='{"margin":"0 10px 0 0"}' class="select">
					<el-select v-model="queryIndex">
						<el-option v-for="(item,index) in queryList" :key="index" :label="item.queryName" :value="index"></el-option>
					</el-select>
				</div>
				<div :style='{"margin":"0 10px 0 0"}' class="input" v-if="queryIndex==0">
					<el-input v-model="yaopinxinxiyaopinmingcheng" placeholder="药品名称"></el-input>
				</div>
				<div :style='{"margin":"0"}' class="btn" v-if="queryIndex==0">
					<el-button :style='{"border":"0","cursor":"pointer","padding":"0 10px","margin":"0","outline":"none","color":"rgba(255, 255, 255, 1)","borderRadius":"4px","background":"rgba(64, 158, 255, 1)","width":"auto","lineHeight":"44px","fontSize":"14px","height":"44px"}' type="primary" @click="search('yaopinxinxi')">
						<span class="icon iconfont icon-fangdajing07" :style='{"color":"rgba(255, 255, 255, 1)","margin":"0 4px 0 0","fontSize":"14px"}'></span>
						搜索
					</el-button>
				</div>
			</div>
			<img v-if="headportrait&&Token" :style='{"width":"40px","margin":"0 12px","borderRadius":"50%","display":"none","height":"40px"}' :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')">
			<div v-if="Token" :style='{"padding":"0 12px","fontSize":"16px","lineHeight":"32px","color":"#000","height":"32px"}'>{{username}}</div>
			<div v-if="Token && notAdmin" :style='{"cursor":"pointer","padding":"0 12px","margin":"0 10px 0 0","color":"#000","background":"#eee","fontSize":"16px","lineHeight":"32px","height":"32px","order":"-1"}' @click="goMenu('/index/center')">个人中心</div>
			<el-button v-if="!Token" @click="toLogin()" :style='{"border":"0","padding":"0 10px","margin":"0 10px","color":"#ff","display":"inline-block","right":"calc(7% + 80px)","borderRadius":"2px","background":"#f1c776","width":"auto","fontSize":"16px","lineHeight":"32px","position":"absolute","height":"32px"}'>登录/注册</el-button>
			<el-button v-if="Token" @click="logout" :style='{"border":"0","padding":"0 10px","margin":"0 10px","color":"#000","display":"inline-block","right":"7%","borderRadius":"2px","background":"#eee","width":"auto","fontSize":"16px","lineHeight":"32px","position":"absolute","height":"32px","order":"50"}'>退出</el-button>
		</div>


			<div class="menu-preview" :style='{"padding":"0 7% 0 37%","borderColor":"#efefef","top":"60px","borderWidth":"0","background":"url(http://codegen.caihongy.cn/20231117/38bcf4d2a9414fcc8bd575a99acd52b3.png) no-repeat right center / auto 100%,#fff8ec","width":"100%","position":"fixed","borderStyle":"solid","height":"auto","zIndex":"111"}'>
			<el-scrollbar wrap-class="scrollbar-wrapper-horizontal">
				<el-menu class="el-menu-horizontal-demo" :style='{"border":0,"listStyle":"none","margin":"0","position":"relative","background":"rgba(0,0,0,0)","display":"flex"}' :default-active="activeMenu" :unique-opened="true" mode="horizontal" :router="true" @select="handleSelect">
					<div class="userinfo" :style='{"width":"84px","padding":"6px 10px 0","display":"none","height":"auto"}'>
					  <el-image :style='{"width":"100%","objectFit":"cover","borderRadius":"20px","display":"block","height":"32px"}' :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')" fit="cover"></el-image>
					  <div :style='{"fontSize":"12px","lineHeight":"1.5","color":"#333","textAlign":"center"}'>{{username}}</div>
					</div>
					<el-menu-item class="home" index="/index/home" @click.native="goMenu('/index/home')">
						<span :style='{"padding":"0 10px","margin":"0","color":"inherit","display":"none","width":"14px","lineHeight":"56px","fontSize":"14px","height":"56px"}' class="icon iconfont icon-shouye-zhihui"></span>
						<span :style='{"padding":"0 10px","lineHeight":"56px","fontSize":"14px","color":"inherit","height":"56px"}'>系统首页</span>
					</el-menu-item>
					<el-menu-item class="item" v-for="(menu, index) in menuList" :index="menu.url" :key="index" @click.native="goMenu(menu.url)">
						<i :style='{"padding":"0 10px","margin":"0","color":"inherit","display":"none","width":"14px","lineHeight":"56px","fontSize":"14px","height":"56px"}' :class="iconArr[index]"></i>
						<span :style='{"padding":"0 10px","lineHeight":"56px","fontSize":"14px","color":"inherit","height":"56px"}'>{{menu.name}}</span>
					</el-menu-item>
					<el-menu-item class="service" @click="goChat">
						<span :style='{"padding":"0 10px","margin":"0","color":"inherit","width":"14px","lineHeight":"56px","fontSize":"14px","height":"56px"}' class="icon iconfont icon-touxiang09"></span>
						<span :style='{"padding":"0 10px","lineHeight":"56px","fontSize":"14px","color":"inherit","height":"56px"}'>
							客服中心
						</span>
					</el-menu-item>
					<el-menu-item class="shop" index="/index/cart" @click.native="goMenu('/index/cart')">
						<span :style='{"padding":"0 10px","margin":"0","color":"inherit","width":"14px","lineHeight":"56px","fontSize":"14px","height":"56px"}' class="icon iconfont icon-wuliu8"></span>
						<span :style='{"padding":"0 10px","lineHeight":"56px","fontSize":"14px","color":"inherit","height":"56px"}'>购物车</span>
					</el-menu-item>
					<el-menu-item class="user" index="/index/center" v-if="Token && notAdmin" @click.native="goMenu('/index/center')">
						<span :style='{"padding":"0 10px","margin":"0","color":"inherit","width":"14px","lineHeight":"56px","fontSize":"14px","height":"56px"}' class="icon iconfont icon-shouye-zhihui"></span>
						<span :style='{"padding":"0 10px","lineHeight":"56px","fontSize":"14px","color":"inherit","height":"56px"}'>个人中心</span>
					</el-menu-item>
				</el-menu>
			</el-scrollbar>
			</div>




			<div class="swiper3" :style='{"width":"100%","margin":"0 auto","height":"auto"}'>
			  <div class="swiper-container mySwiper3">
			    <div class="swiper-wrapper">
			      <div class="swiper-slide" v-for="item in carouselList" :key="item.id">
			        <div :style='{"width":"100%","height":"auto"}'>
			          <el-image @click="carouselClick(item.url)" :style='{"objectFit":"cover","width":"100%","height":"400px"}' :src="baseUrl + item.value" fit="cover"></el-image>
			        </div>
			      </div>
			    </div>
			    <!-- Add Pagination -->
			    <div class="swiper-pagination" :style='{"width":"100%","left":"0","bottom":"10px"}'></div>
			    <!-- Add Arrows -->
			    <div class="swiper-button-next" :style='{"width":"24px","margin":"-12px 7% 0 0","top":"50%","height":"24px"}'>
			      <span class="icon iconfont icon-jiantou18" :style='{"width":"24px","fontSize":"24px","color":"#fff","height":"24px"}'></span>
			    </div>
			    <div class="swiper-button-prev" :style='{"width":"24px","margin":"-12px 0 0 7%","top":"50%","height":"24px"}'>
			      <span class="icon iconfont icon-jiantou39" :style='{"width":"24px","fontSize":"24px","color":"#fff","height":"24px"}'></span>
			    </div>
			  </div>
			</div>
			<router-view id="scrollView"></router-view>
			
			<div class="bottom-preview" :style='{"width":"100%","height":"auto"}'>
				<div :style='{"width":"100%","padding":"20px","overflow":"hidden","color":"#FFFFFF","background":"#000000","height":"auto"}'><div v-html="bottomContent"></div></div>
			</div>
		</div>
		
        <el-dialog title="客服中心" :visible.sync="chatFormVisible" width="600px" :before-close="chatClose">
            <div class="chat-content" id="chat-content">
                <div v-bind:key="item.id" v-for="item in chatList">
                    <div v-if="item.ask" class="right-content">
                        <el-alert v-if="!item.img" class="text-content" :title="item.ask" :closable="false"
                            type="warning"></el-alert>
                        <el-image v-if="item.img" :src="baseUrl + item.img" style="width: 120px;margin: 10px;"
                            fit="scale-down" :preview-src-list="[baseUrl + item.img]"></el-image>
                    </div>
                    <div v-else class="left-content">
                        <el-alert v-if="!item.img" class="text-content" :title="item.reply" :closable="false"
                            type="success"></el-alert>
                        <el-image v-if="item.img" :src="baseUrl + item.img" style="width: 120px;margin: 10px;"
                            fit="scale-down" :preview-src-list="[baseUrl + item.img]"></el-image>
                    </div>
                    <div class="clear-float"></div>
                </div>
            </div>
            <div slot="footer" class="dialog-footer">
                <div v-if="askShow"
                    style="padding-bottom: 10px;display: flex;align-items: center;justify-content: center;">
                    <el-upload class="upload-demo" :action="uploadUrl" :on-success="uploadSuccess" 
                        :show-file-list="false">
                        <el-button type="success">上传图片</el-button>
                    </el-upload>

                    <el-button type="primary" style="margin-left: 20px;" @click="askChange">
                        转{{askType==1?'人工服务':'智能回复'}}</el-button>
                </div>
                <div style="width: 100%;display: flex;align-items: center;justify-content: space-between;">
                    <img style="width: 30px;cursor: pointer;" @click="askShow = !askShow" src="../assets/jiahao.png">
                    <el-input v-model="form.ask" placeholder="请输入内容" style="width: calc(100% - 110px);">
                    </el-input>
                    <el-button type="primary" @click="addChat(null)">发送</el-button>
                </div>

                <!-- <el-input v-model="form.ask" placeholder="请输入内容" style="width: calc(100% - 80px);float: left;">
                </el-input>
                <el-button type="primary" @click="addChat">发送</el-button> -->
            </div>
        </el-dialog>
	</div>
</template>

<script>
import Vue from 'vue'
import Swiper from "swiper";
import axios from 'axios'

export default {
    data() {
		return {
		queryList:[
		  {
			  queryName:"药品名称",
		  },
		],
		queryIndex: 0,
		yaopinxinxiyaopinmingcheng: '',
            activeIndex: '0',
			roleMenus: [{"backMenu":[{"child":[{"allButtons":["新增","查看","修改","删除","首页总数"],"appFrontIcon":"cuIcon-attentionfavor","buttons":["新增","查看","修改","删除","首页总数"],"menu":"用户","menuJump":"列表","tableName":"yonghu"}],"menu":"用户管理"},{"child":[{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-shop","buttons":["新增","查看","修改","删除"],"menu":"药品分类","menuJump":"列表","tableName":"yaopinfenlei"}],"menu":"药品分类管理"},{"child":[{"allButtons":["新增","查看","修改","删除","药品库存统计","药品分类统计","查看评论","首页总数","首页统计","药品入库"],"appFrontIcon":"cuIcon-present","buttons":["新增","查看","修改","删除","查看评论","首页总数","首页统计","药品入库"],"menu":"药品信息","menuJump":"列表","tableName":"yaopinxinxi"}],"menu":"药品信息管理"},{"child":[{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-shop","buttons":["查看","修改","删除"],"menu":"入库信息","menuJump":"列表","tableName":"rukuxinxi"}],"menu":"入库信息管理"},{"child":[{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-service","buttons":["新增","查看","修改","删除"],"menu":"客服中心","tableName":"chat"},{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-news","buttons":["新增","查看","修改","删除"],"menu":"智能助手","tableName":"chathelper"},{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-rank","buttons":["查看","修改"],"menu":"轮播图管理","tableName":"config"},{"allButtons":["查看","修改"],"appFrontIcon":"cuIcon-copy","buttons":["查看","修改"],"menu":"关于我们","tableName":"aboutus"},{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-news","buttons":["新增","查看","修改","删除"],"menu":"公告资讯","tableName":"news"},{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-addressbook","buttons":["新增","查看","修改","删除"],"menu":"友情链接","tableName":"friendlink"},{"allButtons":["新增","查看","修改","删除"],"appFrontIcon":"cuIcon-news","buttons":["新增","查看","修改","删除"],"menu":"公告资讯分类","tableName":"newstype"}],"menu":"系统管理"},{"child":[{"allButtons":["新增","查看","修改","删除","导出","日销量","月销量","年销量","品销量","类销量","日销额","月销额","年销额","品销额","类销额"],"appFrontIcon":"cuIcon-news","buttons":["查看"],"menu":"已取消订单","tableName":"orders/已取消"},{"allButtons":["新增","查看","修改","删除","导出","日销量","月销量","年销量","品销量","类销量","日销额","月销额","年销额","品销额","类销额","物流"],"appFrontIcon":"cuIcon-phone","buttons":["查看"],"menu":"已退款订单","tableName":"orders/已退款"},{"allButtons":["新增","查看","修改","删除","导出","日销量","月销量","年销量","品销量","类销量","日销额","月销额","年销额","品销额","类销额","确认收货","物流"],"appFrontIcon":"cuIcon-skin","buttons":["查看"],"menu":"已发货订单","tableName":"orders/已发货"},{"allButtons":["新增","查看","修改","删除","导出","日销量","月销量","年销量","品销量","类销量","日销额","月销额","年销额","品销额","类销额"],"appFrontIcon":"cuIcon-news","buttons":["查看"],"menu":"未支付订单","tableName":"orders/未支付"},{"allButtons":["新增","查看","修改","删除","导出","日销量","月销量","年销量","品销量","类销量","日销额","月销额","年销额","品销额","类销额","发货","物流","核销"],"appFrontIcon":"cuIcon-pic","buttons":["查看","发货","物流"],"menu":"已支付订单","tableName":"orders/已支付"},{"allButtons":["新增","查看","修改","删除","导出","日销量","月销量","年销量","品销量","类销量","日销额","月销额","年销额","品销额","类销额","物流","退货审核"],"appFrontIcon":"cuIcon-link","buttons":["查看","退货审核","物流","品销额","类销额"],"menu":"已完成订单","tableName":"orders/已完成"}],"menu":"订单管理"}],"frontMenu":[{"child":[{"allButtons":["新增","查看","修改","删除","药品库存统计","药品分类统计","查看评论","首页总数","首页统计","药品入库"],"appFrontIcon":"cuIcon-circle","buttons":["查看"],"menu":"药品信息列表","menuJump":"列表","tableName":"yaopinxinxi"}],"menu":"药品信息模块"}],"hasBackLogin":"是","hasBackRegister":"否","hasFrontLogin":"否","hasFrontRegister":"否","roleName":"管理员","tableName":"users"},{"backMenu":[],"frontMenu":[{"child":[{"allButtons":["新增","查看","修改","删除","药品库存统计","药品分类统计","查看评论","首页总数","首页统计","药品入库"],"appFrontIcon":"cuIcon-circle","buttons":["查看"],"menu":"药品信息列表","menuJump":"列表","tableName":"yaopinxinxi"}],"menu":"药品信息模块"}],"hasBackLogin":"否","hasBackRegister":"否","hasFrontLogin":"是","hasFrontRegister":"是","roleName":"用户","tableName":"yonghu"}],
			baseUrl: '',
			carouselList: [],
			menuList: [],
			chatFormVisible: false,
			chatList: [],
            askType: 1, //1为智能回复 2为人工服务
            uploadUrl: this.$config.baseUrl + 'file/upload',
            headers: {
                Token: localStorage.getItem('frontToken')
            },
            askShow: false,
			form: {
				ask: '',
				userid: localStorage.getItem('frontUserid')
			},
			headportrait: localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'',
			Token: localStorage.getItem('frontToken'),
            username: localStorage.getItem('username'),
            notAdmin: localStorage.getItem('frontSessionTable')!='"users"',
			timer: '',
			// 天气
			weather: {},
			iconArr: [
				'el-icon-star-off',
				'el-icon-goods',
				'el-icon-warning',
				'el-icon-question',
				'el-icon-info',
				'el-icon-help',
				'el-icon-picture-outline-round',
				'el-icon-camera-solid',
				'el-icon-video-camera-solid',
				'el-icon-video-camera',
				'el-icon-bell',
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-operation',
				'el-icon-s-promotion',
				'el-icon-s-release',
				'el-icon-s-ticket',
				'el-icon-s-management',
				'el-icon-s-open',
				'el-icon-s-shop',
				'el-icon-s-marketing',
				'el-icon-s-flag',
				'el-icon-s-comment',
				'el-icon-s-finance',
				'el-icon-s-claim',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check'
			],
			bottomContent: '',
		}
    },
    created() {
		// 获取天气
		this.getWeather()
		this.baseUrl = this.$config.baseUrl;
		this.menuList = this.$config.indexNav;
		this.getCarousel();
        if(localStorage.getItem('frontToken') && localStorage.getItem('frontToken')!=null) {
			this.getSession()
            this.saveChathelper('主人，我是您的智能助手小搏，请问有什么可以帮您！');
            this.getChatList();
        }
    },
    mounted() {
        this.activeIndex = localStorage.getItem('keyPath') || '0';


		// banner
		setTimeout(()=>{
			new Swiper(".mySwiper3", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"autoplay":{"delay":2500,"disableOnInteraction":false},"pagination":{"el":".swiper-pagination","clickable":true}})
		}, 500)

    },
    computed: {
		activeMenu() {
			const route = this.$route
			const {
				meta,
				path
			} = route
			// if st path, the sidebar will highlight the path you sete
			if (meta.activeMenu) {
				return meta.activeMenu
			}
			return path
		},
    },
    watch: {
        $route(newValue) {
            let that = this
            let url = window.location.href
            let arr = url.split('#')
            for (let x in this.menuList) {
                if (newValue.path == this.menuList[x].url) {
                    this.activeIndex = x
                }
            }
            this.Token = localStorage.getItem('frontToken')
            if(arr[1]!='/index/home'){
            	var element = document.getElementById('scrollView');
            	var distance = element.offsetTop;
            	window.scrollTo( 0, distance )
            }else{
            	window.scrollTo( 0, 0 )
            }
        },
		headportrait(){
			this.$forceUpdate()
		},
    },
    methods: {
      search(tablename) {
        if (this.queryIndex == 0 && this.yaopinxinxiyaopinmingcheng) {
          this.$router.push({path: '/index/' + tablename, query: {indexQueryCondition: this.yaopinxinxiyaopinmingcheng}});
        }
      },
		// 获取当前城市天气
		getWeather(){
			axios({
				method: 'get',
				url: 'http://v0.yiketianqi.com/free/day?appid=69475998&appsecret=rldbX1Zl'
			}).then(res => {
				this.weather = res.data
			})
		},

		async getSession() {
			await this.$http.get(`${localStorage.getItem('UserTableName')}/session`, {emulateJSON: true}).then(async res => {
				if (res.data.code == 0) {
					localStorage.setItem('sessionForm',JSON.stringify(res.data.data))
					localStorage.setItem('frontUserid', res.data.data.id);
					if(res.data.data.vip) {
						localStorage.setItem('vip', res.data.data.vip);
					}
					if(res.data.data.touxiang) {
						this.headportrait = res.data.data.touxiang
						localStorage.setItem('frontHeadportrait', res.data.data.touxiang);
					} else if(res.data.data.headportrait) {
						this.headportrait = res.data.data.headportrait
						localStorage.setItem('frontHeadportrait', res.data.data.headportrait);
					}
				}
			});
		},
        handleSelect(keyPath) {
            if (keyPath) {
                localStorage.setItem('keyPath', keyPath)
            }
        },
		toLogin() {
		  this.$router.push('/login');
		},
        logout() {
            localStorage.clear();
            Vue.http.headers.common['Token'] = "";
            this.$router.push('/index/home');
            this.activeIndex = '0'
            localStorage.setItem('keyPath', this.activeIndex)
            this.Token = ''
            this.$forceUpdate()
            this.$message({
                message: '登出成功',
                type: 'success',
                duration: 1000,
            });
        },
		getCarousel() {
			this.$http.get('config/list', {params: { page: 1, limit: 3 }}).then(res => {
				if (res.data.code == 0) {
					this.carouselList = res.data.data.list;
				}
			});
		},
		// 轮播图跳转
		carouselClick(url) {
			if (url) {
				if (url.indexOf('https') != -1) {
					window.open(url)
				} else {
					this.$router.push(url)
				}
			}
		},
		goBackend() {
			localStorage.setItem('Token', localStorage.getItem('frontToken'));
			localStorage.setItem('role', localStorage.getItem('frontRole'));
			localStorage.setItem('sessionTable', localStorage.getItem('frontSessionTable'));
			localStorage.setItem('headportrait', localStorage.getItem('frontHeadportrait'));
			localStorage.setItem('userid', localStorage.getItem('frontUserid'));
			window.open(`${this.$config.baseUrl}admin/dist/index.html`, "_blank");
		},
		getChatList() {
			this.$http.get('chat/list', {params: { userid: localStorage.getItem('frontUserid'), sort: 'addtime', order: 'asc',limit: 1000 }}).then(res => {
				if (res.data.code == 0) {
                    for (let x in res.data.data.list) {
                        if (res.data.data.list[x].ask) {
                            if (res.data.data.list[x].ask.split('/').length > 1) {
                                res.data.data.list[x].img = res.data.data.list[x].ask
                            }
                        }
                        if (res.data.data.list[x].reply) {
                            if (res.data.data.list[x].reply.split('/').length > 1) {
                                res.data.data.list[x].img = res.data.data.list[x].reply
                            }
                        }
                    }
					this.chatList = res.data.data.list;
                    let div = document.getElementsByClassName('chat-content')[0]
                    setTimeout(() => {
                        if (div)
                            div.scrollTop = div.scrollHeight
                    }, 0)
				}
			});
		},
        addChat(ask = null) {
            this.$http.post('chat/add', ask ? {
                ask: ask,
                userid: localStorage.getItem('frontUserid')
            } : {
				ask: this.form.ask,
				userid: localStorage.getItem('frontUserid')
			}).then(res => {
                if (res.data.code == 0) {
                    if (this.askType == 1 && ask == null) {
                        let ask = JSON.parse(JSON.stringify(this.form.ask))
                        this.$nextTick(function() {
                            setTimeout(() => {
                                this.getChathelper(ask)
                            }, 1000) // 要加点延迟, 不然有可能不生效
                        })
                    }
                    this.form.ask = '';
                    this.getChatList();
                }
            });
        },
        getChathelper(ask) {
            this.$http.get('chathelper/page', {
                params: {
                    ask: ask,
                    limit: 1
                }
            }).then(res => {
                if (res.data.code == 0) {
                    if (res.data.data.list.length) {
                        this.saveChathelper(res.data.data.list[0].reply)
                    } else {
                        this.saveChathelper('主人，小搏还不够聪明，无法理解您的意思！')
                    }
                }
            });
        },
        saveChathelper(reply) {
            this.$http.post('chat/save', {
                reply: reply,
                userid: localStorage.getItem('frontUserid')
            }).then(res => {
                if (res.data.code == 0) {
                    this.form.ask = '';
                    this.getChatList();
                }
            });
        },
        askChange() {
            this.askShow = !this.askShow;
            this.askType = this.askType == 1 ? 2 : 1
            if(this.askType==1) {
                this.saveChathelper('主人，我是您的智能助手小搏，请问有什么可以帮您！');
            } else if(this.askType==2){
                this.saveChathelper('您好，在线客服很高兴为您服务！');
            }
        },
        uploadSuccess(res) {
            if (res.code == 0) {
                this.askShow = !this.askShow;
                this.addChat('upload/' + res.file)
            }
        },
		chatClose() {
			clearInterval(this.timer);
			this.chatFormVisible = false;
		},
		goChat() {
            if(!localStorage.getItem('frontToken')) {
                this.toLogin();
                return;
            }
			this.chatFormVisible = true;
			this.timer = setInterval(this.getChatList, 2000);
		},
		goMenu(path) {
            this.$router.push(path);
		},
    }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.menu-preview {
	  .el-scrollbar {
	    height: 100%;
	  
	    & /deep/ .scrollbar-wrapper-vertical {
	      overflow-x: hidden;
	    }
	  
	    & /deep/ .scrollbar-wrapper-horizontal {
	      overflow-y: hidden;
	  
	      .el-scrollbar__view {
	        white-space: nowrap;
	      }
	    }
	  }
	}
	
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.home {
				cursor: pointer;
				border: 0;
				padding: 0 20px;
				color: #333;
				white-space: nowrap;
				display: flex;
				font-size: 14px;
				line-height: 60px;
				background: rgba(0,0,0,0);
				width: 150px;
				align-items: center;
				position: relative;
				list-style: none;
				height: 60px;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.home:hover {
				color: #000;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.home.is-active {
				color: #000;
				background: url(http://codegen.caihongy.cn/20231117/c54b1f882ba14a6b9a90de5cce5f0063.png) 100% 100%;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.user {
				cursor: pointer;
				border: 0;
				padding: 0 20px;
				color: #333;
				white-space: nowrap;
				display: none;
				font-size: 14px;
				line-height: 56px;
				background: #fff;
				align-items: center;
				position: relative;
				list-style: none;
				height: 56px;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.user:hover {
				color: #fff;
				background: red;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.user.is-active {
				color: #fff;
				background: blue;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.service {
				cursor: pointer;
				border: 0;
				padding: 0 20px;
				color: #333;
				white-space: nowrap;
				display: none;
				font-size: 14px;
				line-height: 56px;
				background: #fff;
				align-items: center;
				position: relative;
				list-style: none;
				height: 56px;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.service:hover {
				color: #fff;
				background: red;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.service.is-active {
				color: #fff;
				background: blue;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.shop {
				cursor: pointer;
				border: 0;
				padding: 0 20px;
				color: #333;
				white-space: nowrap;
				display: none;
				font-size: 14px;
				line-height: 56px;
				background: #fff;
				align-items: center;
				position: relative;
				list-style: none;
				height: 56px;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.shop:hover {
				color: #fff;
				background: red;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.shop.is-active {
				color: #fff;
				background: blue;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.back {
				cursor: pointer;
				border: 0;
				padding: 0 20px;
				color: #333;
				white-space: nowrap;
				display: none;
				font-size: 14px;
				line-height: 56px;
				background: #fff;
				align-items: center;
				position: relative;
				list-style: none;
				height: 56px;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.back:hover {
				color: #fff;
				background: red;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.back.is-active {
				color: #fff;
				background: blue;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.item {
				cursor: pointer;
				border: 0;
				padding: 0 20px;
				color: #7F7F7F;
				white-space: nowrap;
				display: flex;
				font-size: 14px;
				line-height: 60px;
				background: rgba(0,0,0,0);
				width: 150px;
				align-items: center;
				position: relative;
				list-style: none;
				height: 60px;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.item:hover {
				color: #000;
			}
	
	.menu-preview .el-menu-horizontal-demo .el-menu-item.item.is-active {
				color: #000;
				background: url(http://codegen.caihongy.cn/20231117/c54b1f882ba14a6b9a90de5cce5f0063.png) 100% 100%;
			}
	
	.banner-preview {
	  .el-carousel /deep/ .el-carousel__indicator button {
	    width: 0;
	    height: 0;
	    display: none;
	  }
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--left {
		width: 36px;
		font-size: 12px;
		height: 36px;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--left:hover {
		background: #7F7F7F;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--right {
		width: 36px;
		font-size: 12px;
		height: 36px;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__container .el-carousel__arrow--right:hover {
		background: #7F7F7F;
	}

	.banner-preview .el-carousel /deep/ .el-carousel__indicators {
		padding: 0;
		margin: 0 0 50px;
		z-index: 2;
		position: absolute;
		list-style: none;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__indicators li {
		border-radius: 10px;
		padding: 0;
		margin: 0 4px;
		background: #fff;
		display: inline-block;
		width: 12px;
		opacity: 0.4;
		transition: 0.3s;
		height: 12px;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__indicators li:hover {
		padding: 0;
		margin: 0 4px;
		background: #fff;
		display: inline-block;
		width: 24px;
		opacity: 0.7;
		height: 12px;
	}
	
	.banner-preview .el-carousel /deep/ .el-carousel__indicators li.is-active {
		padding: 0;
		margin: 0 4px;
		background: #fff;
		display: inline-block;
		width: 24px;
		opacity: 1;
		height: 12px;
	}

    .chat-content {
        padding-bottom: 20px;
        width: 100%;
        margin-bottom: 10px;
        max-height: 300px;
        height: 300px;
        overflow-y: scroll;
        border: 1px solid #eeeeee;
        background: #fff;

        .left-content {
            float: left;
            margin-bottom: 10px;
            padding: 10px;
            max-width: 80%;
        }

        .right-content {
            float: right;
            margin-bottom: 10px;
            padding: 10px;
            max-width: 80%;
        }
    }

    .clear-float {
        clear: both;
    }


	.swiper3 .swiper-button-prev:after {
      display:none;
    }
    .swiper3 .swiper-button-next:after {
      display:none;
    }
	.main-containers .swiper3 .swiper-pagination /deep/ span.swiper-pagination-bullet {
				border-radius: 100%;
				margin: 0 4px;
				background: #000;
				display: inline-block;
				width: 8px;
				opacity: .2;
				height: 8px;
			}
	
	.main-containers .swiper3 .swiper-pagination /deep/ span.swiper-pagination-bullet:hover {
				background: #fff;
				opacity: 1;
			}
	
	.main-containers .swiper3 .swiper-pagination /deep/ span.swiper-pagination-bullet.swiper-pagination-bullet-active {
				background: #fff;
				opacity: 1;
			}
	
	// -------- search --------
	.main-containers .search .select /deep/ .el-input__inner {
				border: 0;
				border-radius: 4px;
				padding: 0 30px 0 10px;
				box-shadow: 0 0 6px rgba(64, 158, 255, .3);
				outline: none;
				color: rgba(64, 158, 255, 1);
				width: 180px;
				font-size: 14px;
				height: 44px;
			}
	
	.main-containers .search .input /deep/ .el-input__inner {
				border: 0;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: 0 0 6px rgba(64, 158, 255, .3);
				outline: none;
				color: rgba(64, 158, 255, 1);
				width: 180px;
				font-size: 14px;
				height: 44px;
			}
	// -------- search --------
	
	.main-containers .btn-service {
				border: 0;
				padding: 0 8px;
				margin: 0 10px;
				color: #000000;
				background: #F9F9F9;
				width: auto;
				font-size: 16px;
				line-height: 32px;
				height: 32px;
			}
	
	.main-containers .btn-service:hover {
				color: #000;
				background: #F9F9F9;
			}
	
	.main-containers .btn-shop {
				border: 0;
				padding: 0 8px;
				margin: 0 10px;
				color: #000000;
				background: #F9F9F9;
				width: auto;
				font-size: 16px;
				line-height: 32px;
				height: 32px;
			}
	
	.main-containers .btn-shop:hover {
				color: #000;
				background: #F9F9F9;
			}
</style>
