<template>
<div class="home-preview" :style='{"width":"100%","margin":"10px auto","flexDirection":"column","display":"flex"}'>

	<!-- 搜索 -->
	<div id="search" class="search animate__animated" :style='{"padding":"10px","margin":"0 auto 10px","background":"#FFC054","display":"flex","width":"86%","justifyContent":"center","height":"auto"}'>
		<div :style='{"margin":"0"}' class="select">
			<el-select v-model="queryIndex">
				<el-option
				  v-for="(item,index) in queryList"
				  :key="index"
				  :label="item.queryName"
				  :value="index"
				></el-option>
			</el-select>
		</div>
		<div :style='{"width":"calc(100% - 300px)","margin":"0 10px 0 0"}' class="input" v-if="queryIndex==0">
			<el-input v-model="yaopinxinxiyaopinmingcheng" placeholder="药品名称"></el-input>
		</div>
		<div :style='{"margin":"0"}' class="btn" v-if="queryIndex==0">
			<el-button :style='{"border":"0","cursor":"pointer","padding":"0 20px","margin":"0","outline":"none","color":"#ffc054","borderRadius":"20px","background":"#FFFFFF","width":"auto","lineHeight":"38px","fontSize":"14px","height":"38px"}' type="primary" @click="search('yaopinxinxi')">
				<span class="icon iconfont icon-fangdajing07" :style='{"color":"#ffc054","margin":"0 4px 0 0","fontSize":"14px"}'></span>
				搜索
			</el-button>
		</div>
	</div>
	<!-- 搜索 -->


		<!-- 关于我们 -->
		<div id="about" class="animate__animated" :style='{"padding":"0 7%","boxShadow":"0 1px 6px rgba(255, 255, 255, .3)","margin":"0","alignItems":"center","flexDirection":"column","display":"flex","justifyContent":"center","flexWrap":"wrap","background":"#ffc054","width":"100%","position":"relative","height":"520px","order":"2"}'>
		  <div :style='{"width":"35%","margin":"0 0 10px 65%","lineHeight":"1.5","fontSize":"40px","color":"#333","order":"1"}'>{{aboutUsDetail.title}}</div>
		  <div :style='{"width":"35%","margin":"0 0 10px 65%","lineHeight":"1.5","fontSize":"20px","color":"#7F7F7F","order":"2"}'>{{aboutUsDetail.subtitle}}</div>
		  <div :style='{"padding":"0","flexWrap":"wrap","left":"0","display":"flex","width":"60%","position":"absolute","height":"520px"}'>
		    <img :style='{"width":"100%","margin":"0","objectFit":"cover","display":"block","height":"100%"}' :src="baseUrl + aboutUsDetail.picture1">
		    <img :style='{"margin":"0 10px","objectFit":"cover","flex":1,"display":"none","height":"120px"}' :src="baseUrl + aboutUsDetail.picture2">
		    <img :style='{"margin":"0 10px","objectFit":"cover","flex":1,"display":"none","height":"120px"}' :src="baseUrl + aboutUsDetail.picture3">
		  </div>
		  <div :style='{"padding":"0","margin":"0 0 0 65%","color":"#FFFFFF","width":"35%","lineHeight":"2","fontSize":"16px","order":"3"}' v-html="aboutUsDetail.content"></div>
		  <div :style='{"width":"285px","background":"url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat","display":"none","height":"100px"}' />
		  <div :style='{"width":"285px","background":"url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat","display":"none","height":"100px"}' />
		  <div :style='{"width":"285px","background":"url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat","display":"none","height":"100px"}' />
		  <div :style='{"width":"285px","background":"url(http://codegen.caihongy.cn/20201114/7856ba26477849ea828f481fa2773a95.jpg) 0% 0% / cover no-repeat","display":"none","height":"100px"}' />
		  <div :style='{"border":"0","margin":"10px auto","textAlign":"center","background":"#409EFF","display":"block","width":"80px","lineHeight":"32px","order":"4"}' @click="toDetail('aboutusDetail',aboutUsDetail)">
		    <span :style='{"color":"#f5f5f5","fontSize":"12px"}'>更多</span>
		    <span class="icon iconfont icon-gengduo1" :style='{"color":"#f5f5f5","fontSize":"12px"}'></span>
		  </div>
		</div>
		<!-- 关于我们 -->

		
	<!-- 新闻资讯 -->
	<div id="animate_newsnews" class="news animate__animated" :style='{"width":"86%","padding":"150px 0 50px","margin":"0 auto","position":"relative","background":"#fff","order":"7"}'>
		<div v-if="false" class="idea newsIdea" :style='{"padding":"20px","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
			<div class="box1" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
			<div class="box2" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
			<div class="box3" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
			<div class="box4" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
			<div class="box5" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
			<div class="box6" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
			<div class="box7" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
			<div class="box8" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
			<div class="box9" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
			<div class="box10" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		</div>
		
		<div class="title" :style='{"margin":"10px auto","top":"30px","textAlign":"center","backgroundImage":"url(http://codegen.caihongy.cn/20231123/59ad5a54cea444488a8f44b4855ee871.png)","width":"250px","lineHeight":"100px","backgroundSize":"120px 90px","position":"absolute","backgroundRepeat":"no-repeat"}'>
			<span :style='{"color":"#0A0A0A","fontSize":"30px"}'>公告资讯</span>
		</div>
		
			
			
			
			
			
			
			
			
			
			
		






		<div v-if="newsList.length" class="list list17 index-pv1" :style='{"width":"100%","padding":"10px","flexDirection":"row-reverse","justifyContent":"space-between","display":"flex","height":"auto"}'>
		  <div class="swiper" :style='{"width":"45%","background":"#fff","height":"auto"}'>
		    <div class="swiper-container newsSwiper17 new-list-17news">
		      <div class="swiper-wrapper">
				<template v-for="(item,index) in newsList">
		        <div v-if="index < 3" class="swiper-slide">
		          <div :style='{"width":"100%","position":"relative","height":"auto"}' @click="toDetail('newsDetail', item)">
		            <img :style='{"width":"100%","objectFit":"cover","display":"block","height":"296px"}' :src="baseUrl + item.picture">
		            <div :style='{"alignItems":"center","left":"0","bottom":"0","background":"rgba(0,0,0,.3)","display":"flex","width":"100%","position":"absolute"}'>
		              <div :style='{"padding":"0 10px","lineHeight":"44px","fontSize":"18px","color":"#fff"}'>{{item.title}}</div>
		              <div :style='{"padding":"0 10px"}'>
		                <span class="icon iconfont icon-shijian21" :style='{"margin":"0 2px 0 0","lineHeight":"1.5","fontSize":"12px","color":"#fff"}'></span>
		                <span :style='{"color":"#fff","lineHeight":"1.5","fontSize":"12px"}'>{{item.addtime}}</span>
		              </div>
		            </div>
		          </div>
		        </div>
				</template>
		      </div>
		      <!-- Add Pagination -->
		      <div class="swiper-pagination" :style='{"width":"100%","left":"0","textAlign":"right","bottom":"10px"}'></div>
		    </div>
		  </div>
		  <div class="right" :style='{"width":"53%","height":"auto"}'>
		    <div v-if="newsList.length > 3" :style='{"width":"100%","padding":"10px","boxShadow":"3px 3px 3px rgba(0,0,0,0.1)","margin":"0 0 20px 0","background":"#fff","height":"auto"}' @click="toDetail('newsDetail', newsList[3])">
		      <div :style='{"color":"#333","lineHeight":"1.5","fontSize":"14px"}' class="name">{{newsList[3].title}}</div>
		      <div :style='{"lineHeight":"24px","fontSize":"14px","overflow":"hidden","color":"#333","height":"24px"}' class="desc">{{newsList[3].introduction}}</div>
		      <div :style='{"padding":"0","display":"inline-block"}'>
		        <span class="icon iconfont icon-shijian21" :style='{"margin":"0 2px 0 0","lineHeight":"1.5","fontSize":"12px","color":"#666"}'></span>
		        <span :style='{"color":"#666","lineHeight":"1.5","fontSize":"12px"}'>{{newsList[3].addtime}}</span>
		      </div>
		      <div :style='{"padding":"0","display":"inline-block"}'>
		        <span class="icon iconfont icon-geren16" :style='{"margin":"0 2px 0 0","lineHeight":"1.5","fontSize":"12px","color":"#666"}'></span>
		        <span :style='{"color":"#666","lineHeight":"1.5","fontSize":"12px"}'>{{newsList[3].name}}</span>
		      </div>
		      <div :style='{"padding":"0","display":"inline-block"}'>
		        <span class="icon iconfont icon-zan10" :style='{"margin":"0 2px 0 0","lineHeight":"1.5","fontSize":"12px","color":"#666"}'></span>
		        <span :style='{"color":"#666","lineHeight":"1.5","fontSize":"12px"}'>{{newsList[3].thumbsupnum}}</span>
		      </div>
		      <div :style='{"padding":"0","display":"inline-block"}'>
		        <span class="icon iconfont icon-shoucang10" :style='{"margin":"0 2px 0 0","lineHeight":"1.5","fontSize":"12px","color":"#666"}'></span>
		        <span :style='{"color":"#666","lineHeight":"1.5","fontSize":"12px"}'>{{newsList[3].storeupnum}}</span>
		      </div>
		      <div :style='{"padding":"0","display":"inline-block"}'>
		        <span class="icon iconfont icon-chakan9" :style='{"margin":"0 2px 0 0","lineHeight":"1.5","fontSize":"12px","color":"#666"}'></span>
		        <span :style='{"color":"#666","lineHeight":"1.5","fontSize":"12px"}'>{{newsList[3].clicknum}}</span>
		      </div>
		    </div>
		    <div class="list" :style='{"width":"100%","padding":"10px","background":"#fff","height":"auto"}'>
			  <template v-for="(item,index) in newsList">
		      <div v-if="index > 3" :style='{"width":"100%","boxShadow":"0 2px 0 rgba(0,0,0,0.1)","alignItems":"center","display":"flex","height":"auto"}' @click="toDetail('newsDetail', item)">
		        <div :style='{"width":"4px","margin":"0 6px","borderRadius":"100%","background":"red","display":"none","height":"4px"}'></div>
		        <div class="title">{{item.title}}</div>
		        <div :style='{"padding":"0 6px"}'>
		          <span class="icon iconfont icon-shijian21" :style='{"color":"#666","lineHeight":"44px","fontSize":"12px"}'></span>
		          <span :style='{"color":"#666","lineHeight":"44px","fontSize":"12px"}'>{{item.addtime}}</span>
		        </div>
		      </div>
			  </template>
		    </div>
		  </div>
		</div>





		<div @click="moreBtn('news')" :style='{"border":"0","margin":"10px auto","top":"65px","textAlign":"center","left":"250px","background":"#fff","display":"block","width":"80px","lineHeight":"32px","position":"absolute"}'>
			<span :style='{"color":"#9E9E9E","fontSize":"16px"}'>更多</span>
			<i :style='{"color":"#9E9E9E","fontSize":"16px"}' class="icon iconfont icon-gengduo1"></i>
		</div>
		
		</div>
	<!-- 新闻资讯 -->


<!-- 商品推荐 -->
<div id="animate_recommendyaopinxinxi" class="recommend animate__animated" :style='{"minHeight":"450px","padding":"120px 0 0","margin":"0 auto 10px","background":"#fff","width":"86%","position":"relative","order":"1"}'>
	<div v-if="false" class="idea recommendIdea" :style='{"padding":"20px","flexWrap":"wrap","background":"#efefef","justifyContent":"space-between","display":"flex"}'>
		<div class="box1" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box2" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box3" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box4" :style='{"width":"20%","background":"#fff","height":"80px"}'></div>
		<div class="box5" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box6" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box7" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box8" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box9" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
		<div class="box10" :style='{"width":"20%","background":"#fff","display":"none","height":"80px"}'></div>
	</div>
	
    <div class="title" :style='{"padding":"30px 0 0 30px","margin":"10px","top":"40px","textAlign":"left","left":"0","background":"url(http://codegen.caihongy.cn/20231123/59ad5a54cea444488a8f44b4855ee871.png) no-repeat left top /120px 90px","width":"40%","position":"absolute","height":"90px"}'>
		<span :style='{"fontSize":"30px","position":"","color":"#0A0A0A","left":"60px","bottom":"10px"}'>药品信息推荐</span>
	</div>
	
	
	
	
	
	
	
	
	
	



	<div v-if="yaopinxinxiRecommend.length" class="list list12 index-pv1 list12yaopinxinxi" :style='{"width":"100%","padding":"40px 0 0","background":"#fff","height":"auto"}'>
	  <div class="tab" :style='{"padding":"0 0 0 40%","margin":"0","top":"40px","display":"flex","width":"calc(100% - 0px)","position":"absolute","right":"0","justifyContent":"flex-end","height":"auto"}'>
	    <template v-for="item,index in recommendList12yaopinxinxi">
		<div v-if="index < 6" @click="recommendIndexClick12(index + 1, 'yaopinxinxi')" class="item" :class="index + 1 == recommendIndex12yaopinxinxi ? 'active' : ''">
			<img v-if="item.image" :style='{"width":"70px","margin":"0","objectFit":"cover","borderRadius":"10px","display":"block","height":"70px"}' :src="baseUrl + (item.image?item.image.split(',')[0]:'')">
			<div :style='{"color":"inherit","fontSize":"14px"}'>{{item[recommendColumn12yaopinxinxi]}}</div>
		</div>
		</template>
		<div class="more" @click="moreBtn('yaopinxinxi')">
		  <div :style='{"color":"inherit","fontSize":"14px"}'>更多</div>
		  <span class="icon iconfont icon-jiantou25" :style='{"color":"inherit","margin":"0 0 0 2px","fontSize":"14px"}'></span>
		</div>
	  </div>
	  <div class="list" :style='{"margin":"20px 0 0","overflow":"hidden","flex":"1","background":"#fff","display":"flex","height":"auto"}'>
	    <div :style='{"boxShadow":"0px 4px 5px 0px rgba(0,0,0,0.3)","padding":"10px","margin":"10px 1% 10px","overflow":"hidden","width":"16%","position":"relative","height":"195px"}' class="item list-item animation-box" v-for="item,index in yaopinxinxiRecommend" :key="index" @click="toDetail('yaopinxinxiDetail', item)">
			<img :name="item.id" :style='{"width":"100%","objectFit":"cover","display":"block","height":"150px"}' v-if="preHttp(item.yaopintupian)" :src="item.yaopintupian.split(',')[0]" alt="" />
			<img :name="item.id" :style='{"width":"100%","objectFit":"cover","display":"block","height":"150px"}' v-else :src="baseUrl + (item.yaopintupian?item.yaopintupian.split(',')[0]:'')" alt="" />
			<div class="line1" :style='{"padding":"0 10px","overflow":"hidden","color":"#333","textAlign":"center","lineHeight":"30px","fontSize":"18px","height":"30px"}'>{{item.yaopinmingcheng}}</div>
	    </div>
	  </div>
	</div>




	
	<div @click="moreBtn('yaopinxinxi')" :style='{"border":"0","margin":"10px auto","top":"80px","textAlign":"center","left":"260px","background":"#fff","display":"none","width":"80px","lineHeight":"32px","position":"absolute"}'>
		<span :style='{"color":"#9E9E9E","fontSize":"12px"}'>更多</span>
		<i :style='{"color":"#9E9E9E","fontSize":"12px"}' class="icon iconfont icon-gengduo1"></i>
	</div>
	
</div>
<!-- 商品推荐 -->

	
	<!-- 友链 -->
	<div id="friendly" class="friendly animate__animated" :style='{"minHeight":"400px","padding":"10px 7%","margin":"0 auto 10px","background":"#fff","width":"100%","height":"auto","order":"5"}'>
	  <div :style='{"width":"100%","borderColor":"#aaa","borderStyle":"solid","textAlign":"center","borderWidth":"0","height":"auto"}'>
	    <div :style='{"padding":"20px","borderColor":"rgba(64, 158, 255)","margin":"0","color":"#0A0A0A","borderWidth":"0","display":"inline-block","fontSize":"40px","borderStyle":"solid"}'>合作伙伴</div>
	  </div>
	  <div :style='{"width":"100%","padding":"10px 0 0 0","flexWrap":"wrap","justifyContent":"center","display":"flex","height":"auto"}'>
	    <div :style='{"boxShadow":"inset 2px 2px 10px 0px rgba(171,171,171,0.3), 10px 10px 10px 0px #FFF0D6","margin":"0 20px 10px 0","alignItems":"center","flexDirection":"column","display":"flex","width":"calc(12% - 20px)","justifyContent":"center","height":"180px"}' v-for="item in friendLinkList" :key="item.id" @click="friendlinkClick(item.url)">
	      <img :style='{"width":"80%","margin":"0 auto","objectFit":"contain","borderRadius":"4px","display":"block","height":"100px"}' :src="item.picture?(baseUrl + item.picture.split(',')[0]): ''" alt="">
	      <div :style='{"color":"#333","lineHeight":"24px","fontSize":"14px"}'>{{item.name}}</div>
	    </div>
	  </div>
	</div>
	<!-- 友链 -->
</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

  export default {
    //数据集合
    data() {
      return {
        baseUrl: '',
        aboutUsDetail: {},
        queryList:[
          {
              queryName:"药品名称",
          },
        ],
        queryIndex: 0,
        yaopinxinxiyaopinmingcheng: '',
        newsList: [],
        yaopinxinxiRecommend: [],


		newListSwiper17news: null,

		recommendIndex12yaopinxinxi: 0,
		recommendList12yaopinxinxi: [],
		recommendColumn12yaopinxinxi: '',


		friendLinkList: [],
      }
    },
    created() {
		this.baseUrl = this.$config.baseUrl;
		this.getNewsList();
		this.getAboutUs();
		this.getList();
		this.yaopinxinxiRecommendCate()
		this.getFriendLinkList()
    },
	mounted() {
		window.addEventListener('scroll', this.handleScroll)
		setTimeout(()=>{
			this.handleScroll()
		},100)
		
		this.swiperChanges()
	},
	beforeDestroy() {
	  window.removeEventListener('scroll', this.handleScroll)
	},
    //方法集合
    methods: {
		swiperChanges() {
			if (this['newListSwiper17news']) this['newListSwiper17news'].destroy()
			setTimeout(()=>{
				this['newListSwiper17news'] = new Swiper(".new-list-17news", {"pagination":{"el":".swiper-pagination","clickable":true}})
			},750)
		},
		// 获取友链列表
		getFriendLinkList(){
			this.$http.get('friendlink/list', {params:{page: 1,limit: 100}}).then(res => {
			  if(res.data.code == 0) {
			    this.friendLinkList = res.data.data.list
			  }
			})
		},
		// 友链跳转
		friendlinkClick(url){
			if(url){
				window.open(url)
			}
		},
		yaopinxinxiRecommendCate(){
			this.$http.get('yaopinfenlei/list',{}).then(res => {
			  if (res.data.code == 0) {
				this.recommendList12yaopinxinxi = res.data.data.list
			  }
			});
			this.recommendColumn12yaopinxinxi = 'yaopinfenlei'
		},
		recommendIndexClick12(index, name) {
			this['recommendIndex12' + name] = index
			this.getList()
			
			document.querySelectorAll('.recommend .list12' + name + ' .list .item').forEach(el => {
			  el.classList.remove("active")
			})
			setTimeout(() => {
			  document.querySelectorAll('.recommend .list12' + name + ' .list .item').forEach(el => {
			    el.classList.add("active")
			  })
			}, 1);
		},

		listClick14(index, name) {
			this['listIndex14' + name] = index
		},

		handleScroll() {
			let arr = [
				{id:'search',css:'animate__bounce'},
				{id:'about',css:'animate__fadeInBottomRight'},
				{id:'system',css:'animate__slideInLeft'},
				{id:'animate_recommendyaopinxinxi',css:'animate__swing'},
				{id:'animate_newsnews',css:'animate__zoomInLeft'},
				{id:'msgs',css:'animate__fadeInBottomLeft'},
				{id:'friendly',css:'animate__zoomInDown'}
			]
			
			for (let i in arr) {
				let doc = document.getElementById(arr[i].id)
				if (doc) {
					let top = doc.offsetTop
					let win_top = window.innerHeight + window.pageYOffset
					// console.log(top,win_top)
					if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
						// console.log(doc)
						doc.classList.add(arr[i].css)
					}
				}
			}
		},
      preHttp(str) {
          return str && str.substr(0,4)=='http';
      },
      getAboutUs() {
          this.$http.get('aboutus/detail/1', {}).then(res => {
            if(res.data.code == 0) {
              this.aboutUsDetail = res.data.data;
            }
          })
      },
      search(tablename) {
        if (this.queryIndex == 0 && this.yaopinxinxiyaopinmingcheng) {
          this.$router.push({path: '/index/' + tablename, query: {indexQueryCondition: this.yaopinxinxiyaopinmingcheng}});
        }
      },
		getNewsList() {
			let data = {
				page: 1,
				limit: 6,
                sort: 'addtime',
				order: 'desc'
			}
			this.$http.get('news/list', {params: data}).then(res => {
				if (res.data.code == 0) {
					this.newsList = res.data.data.list;
					
					
				}
			});
		},
		getList() {
			let autoSortUrl = "";
			let data = {}
          autoSortUrl = "yaopinxinxi/autoSort";
          if(localStorage.getItem('frontToken')) {
              autoSortUrl = "yaopinxinxi/autoSort2";
          }
			data = {
				page: 1,
				limit: 6,
			}
			if(this.recommendIndex12yaopinxinxi != 0){
				data.yaopinfenlei = this.recommendList12yaopinxinxi[this.recommendIndex12yaopinxinxi - 1].yaopinfenlei
			}
			this.$http.get(autoSortUrl, {params: data}).then(res => {
				if (res.data.code == 0) {
					this.yaopinxinxiRecommend = res.data.data.list;
					
					
					// 商品列表样式五
					
				}
			});
			
		},
		toDetail(path, item) {
			this.$router.push({path: '/index/' + path, query: {id: item.id}});
		},
		moreBtn(path) {
			this.$router.push({path: '/index/' + path});
		}
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		// -------- search --------
		.search .select /deep/ .el-input__inner {
			border: 0;
			border-radius: 20px 0 0 20px;
			padding: 0 30px 0 10px;
			outline: none;
			color: #ffc054;
			width: 130px;
			font-size: 14px;
			height: 38px;
		}
		
		.search .input /deep/ .el-input__inner {
			border: 0;
			border-radius: 0 20px 20px 0 ;
			padding: 0 10px;
			outline: none;
			color: #ffc054;
			width: 100%;
			font-size: 14px;
			height: 38px;
		}
		// -------- search --------
		.recommend {
			.list3 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list3 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
			}
			
			.list3 .swiper-button-next {
				left: auto;
				right: 10px;
			}
			
			.list3 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.list5 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list5 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
        }
        
        .list5 .swiper-button-next {
				left: auto;
				right: 10px;
			}
			
			.list5 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.list5 {
				.swiper-slide-prev {
					position: relative;
					z-index: 3;
				}
		
				.swiper-slide-next {
					position: relative;
					z-index: 3;
				}
		
				.swiper-slide-active {
					position: relative;
					z-index: 5;
				}
			}
			
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
		}
		
		.news {
			.list3 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list3 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
			}
			
			.list3 .swiper-button-next {
				left: auto;
				right: 10px;
			}
			
			.list3 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.list6 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list6 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
			}
			
			.list6 .swiper-button-next {
				left: auto;
				right: 10px;
			}
			
			.list6 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
		}
	
		.lists {
			.list3 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list3 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
			}
			
			.list3 .swiper-button-next {
				left: auto;
				right: 10px;
        }
        
        .list3 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.list5 .swiper-button-prev {
				left: 10px;
				right: auto;
			}
			
			.list5 .swiper-button-prev::after {
				color: rgb(64, 158, 255);
			}
			
			.list5 .swiper-button-next {
            left: auto;
            right: 10px;
			}
			
			.list5 .swiper-button-next::after {
				color: rgb(64, 158, 255);
			}
			
			.list5 {
				.swiper-slide-prev {
					position: relative;
					z-index: 3;
				}
		
				.swiper-slide-next {
					position: relative;
					z-index: 3;
				}
		
				.swiper-slide-active {
					position: relative;
					z-index: 5;
				}
			}
			
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(10deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(10deg) scale(1.1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
		}
	}
	



	.home-preview .news .list17 .newsSwiper17 .swiper-pagination /deep/ span.swiper-pagination-bullet {
				border-radius: 100%;
				margin: 0 4px;
				background: #fff;
				display: inline-block;
				width: 10px;
				opacity: .2;
				height: 10px;
			}
	
	.home-preview .news .list17 .newsSwiper17 .swiper-pagination /deep/ span.swiper-pagination-bullet:hover {
				background: red;
				opacity: 1;
			}
	
	.home-preview .news .list17 .newsSwiper17 .swiper-pagination /deep/ span.swiper-pagination-bullet.swiper-pagination-bullet-active {
				background: red;
				opacity: 1;
			}
	
	.home-preview .news .list17 .right .list .title {
				color: #333;
				flex: 1;
				font-size: 14px;
				line-height: 44px;
				transition: 0.3s;
			}
	
	.home-preview .news .list17 .right .list .title:hover {
				transform: translate3d(10px, 0px, 0px);
			}




	.home-preview .recommend .list12 .tab .item {
				cursor: pointer;
				border: 0;
				padding: 10px;
				margin: 0 10px;
				flex-direction: column;
				color: #333;
				display: flex;
				width: auto;
				font-size: 16px;
				line-height: 40px;
				justify-content: center;
				align-items: center;
			}
	
	.home-preview .recommend .list12 .tab .item:hover {
				color: #000000;
			}
	
	.home-preview .recommend .list12 .tab .item.active {
				color: #000000;
				border-color: #ffc054;
				border-width: 0 0 2px 0;
				border-style: solid;
			}
	
	.home-preview .recommend .list12 .tab .more {
				cursor: pointer;
				padding: 5px 10px;
				margin: 0 10px;
				color: #666;
				background: #fff;
				display: flex;
				line-height: 100px;
				align-items: start;
			}
	
	.home-preview .recommend .list12 .tab .more:hover {
				color: #000000;
			}
	
	.home-preview .recommend .list12 .item.active {
	  animation-name: mymove;
	
	  &:nth-of-type(1) {
	    animation-duration: 1s;
	  }
	  &:nth-of-type(2) {
	    animation-duration: 1.2s;
	  }
	  &:nth-of-type(3) {
	    animation-duration: 1.4s;
	  }
	  &:nth-of-type(4) {
	    animation-duration: 1.6s;
	  }
	  &:nth-of-type(5) {
	    animation-duration: 1.8s;
	  }
	  &:nth-of-type(6) {
	    animation-duration: 2s;
	  }
	}
	
	@keyframes mymove
	{
		from {top: 320px;}
		to {top: 0;}
	}



</style>
