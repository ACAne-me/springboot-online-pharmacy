<template>
<div class="home-preview" :style='{"width":"100%","margin":"0 auto","flexDirection":"column","background":"#f6f6f6","display":"flex"}'>

	<!-- 搜索 -->
	<div id="search" class="search animate__animated" :style='{"border":"1px solid #D8D8D8","padding":"0","margin":"40px auto 0","overflow":"hidden","borderRadius":"10px","flexWrap":"wrap","background":"#fff","display":"flex","width":"80%","height":"auto"}'>
		<div :style='{"margin":"0 10px 0 0"}' class="select">
			<el-select v-model="queryIndex">
				<el-option
				  v-for="(item,index) in queryList"
				  :key="index"
				  :label="item.queryName"
				  :value="index"
				></el-option>
			</el-select>
		</div>
		<div :style='{"margin":"0 10px 0 0","border-left":"1px solid #d8d8d8","flex":"1"}' class="input" v-if="queryIndex==0">
			<el-input v-model="yaopinxinxiyaopinmingcheng" placeholder="药品名称"></el-input>
		</div>
		<div :style='{"margin":"0"}' class="btn" v-if="queryIndex==0">
			<el-button :style='{"border":"0","cursor":"pointer","padding":"0 10px","margin":"0","color":"#9E9E9E","borderRadius":"4px","background":"#fff","width":"auto","lineHeight":"44px","fontSize":"24px","height":"44px"}' type="primary" @click="search('yaopinxinxi')">
				<span class="icon iconfont icon-fangdajing07" :style='{"color":"#9E9E9E","margin":"0 10px 0 0","fontSize":"24px"}'></span>
				
			</el-button>
		</div>
	</div>
	<!-- 搜索 -->
<!-- 商品推荐 -->
<div id="animate_recommendyaopinxinxi" class="recommend animate__animated" :style='{"width":"80%","padding":"40px 0","margin":"0 auto","position":"relative","order":"1"}'>
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
    <div class="title" :style='{"margin":"10px auto 20px","color":"#000000","textAlign":"left","background":"url(http://codegen.caihongy.cn/20231108/785b92a18bec459faffc7c29ddd93c9d.png) no-repeat left center","width":"auto","minWidth":"252px","height":"128px"}'>
		<span :style='{"fontSize":"43px","lineHeight":"180px","color":"#000000","fontWeight":"600"}'>药品信息推荐</span>
	</div>
	<div v-if="yaopinxinxiRecommend.length" class="list list12 index-pv1 list12yaopinxinxi" :style='{"width":"100%","padding":"10px","background":"none","height":"auto"}'>
	  <div class="tab" :style='{"padding":"20px 0","margin":"0 0 20px","borderColor":"#000","borderWidth":"2px 0","display":"flex","width":"100%","borderStyle":"solid","justifyContent":"space-between","height":"auto"}'>
	    <template v-for="item,index in recommendList12yaopinxinxi">
		<div v-if="index < 4" @click="recommendIndexClick12(index + 1, 'yaopinxinxi')" class="item" :class="index + 1 == recommendIndex12yaopinxinxi ? 'active' : ''">
			<img v-if="item.image" :style='{"width":"44px","margin":"0 5px 0 0","objectFit":"cover","display":"block","height":"44px"}' :src="baseUrl + (item.image?item.image.split(',')[0]:'')">
			<div :style='{"color":"inherit","fontSize":"18px"}'>{{item[recommendColumn12yaopinxinxi]}}</div>
		</div>
		</template>
		<div class="more" @click="moreBtn('yaopinxinxi')">
		  <div :style='{"fontSize":"18px","color":"inherit","fontWeight":"600","order":"2"}'>查看更多</div>
		  <span class="icon iconfont icon-qita7" :style='{"border":"2px solid #000","padding":"0 10px","margin":"0 10px 0 2px","fontSize":"26px","color":"inherit","borderRadius":"4px"}'></span>
		</div>
	  </div>
	  <div class="list" :style='{"margin":"40px 0 0","overflow":"hidden","flexWrap":"wrap","background":"none","display":"flex","justifyContent":"space-between","height":"auto"}'>
	    <div :style='{"cursor":"pointer","width":"49%","margin":"0 0 30px","position":"relative","height":"auto"}' class="item list-item animation-box" v-for="item,index in yaopinxinxiRecommend" :key="index" @click="toDetail('yaopinxinxiDetail', item)">
			<img :name="item.id" :style='{"width":"100%","objectFit":"cover","display":"block","height":"288px"}' v-if="preHttp(item.yaopintupian)" :src="item.yaopintupian.split(',')[0]" alt="" />
			<img :name="item.id" :style='{"width":"100%","objectFit":"cover","display":"block","height":"288px"}' v-else :src="baseUrl + (item.yaopintupian?item.yaopintupian.split(',')[0]:'')" alt="" />
			<div class="line1" :style='{"padding":"0 10px","color":"#333","textAlign":"center","background":"#fff","width":"100%","lineHeight":"60px","fontSize":"16px"}'>{{item.yaopinmingcheng}}</div>
	    </div>
	  </div>
	</div>
	<div @click="moreBtn('yaopinxinxi')" :style='{"border":"2px solid #000000","cursor":"pointer","padding":"16px 60px","whiteSpace":"nowrap","top":"90px","textAlign":"center","background":"#fff","display":"block","lineHeight":"1em","position":"absolute","right":"0"}'>
		<span :style='{"color":"#000000","fontSize":"16px","fontWeight":"600"}'>查看更多</span>
		<i :style='{}' class="icon iconfont "></i>
	</div>
	
</div>
<!-- 商品推荐 -->

	
	<!-- 友链 -->
	<div id="friendly" class="friendly animate__animated" :style='{"padding":"60px 10%","margin":"0 auto","background":"#fff","display":"flex","width":"100%","position":"relative","justifyContent":"space-between","height":"auto","order":"5"}'>
	  <div :style='{"width":"auto","margin":"0 5% 0 0","minWidth":"252px","textAlign":"left","background":"url(http://codegen.caihongy.cn/20231108/785b92a18bec459faffc7c29ddd93c9d.png) no-repeat left center","height":"128px"}'>
	    <div :style='{"letterSpacing":"4px","lineHeight":"180px","fontSize":"60px","color":"#000","fontWeight":"600","display":"inline-block"}'>合作伙伴</div>
	  </div>
	  <div :style='{"padding":"0","flexWrap":"wrap","background":"none","flex":"1","display":"flex","width":"100%","justifyContent":"space-between","height":"auto"}'>
	    <div :style='{"cursor":"pointer","margin":"0 0 20px","alignItems":"center","flexDirection":"column","display":"flex","width":"23%","height":"auto"}' v-for="item in friendLinkList" :key="item.id" @click="friendlinkClick(item.url)">
	      <img :style='{"padding":"10px","margin":"0 auto","objectFit":"contain","borderRadius":"4px","background":"#fff","display":"block","width":"120px","height":"120px"}' :src="item.picture?(baseUrl + item.picture.split(',')[0]): ''" alt="">
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



		newsIndex15: 0,
		newsCategoryList15: [],
		recommendIndex12yaopinxinxi: 0,
		recommendList12yaopinxinxi: [],
		recommendColumn12yaopinxinxi: '',


		friendLinkList: [],
      }
    },
    created() {
		this.baseUrl = this.$config.baseUrl;
		this.getNewsList();
		this.getNewsCategory()
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
			setTimeout(()=>{
			},750)
		},
		getFriendLinkList(){
			this.$http.get('friendlink/list', {params:{page: 1,limit: 100}}).then(res => {
			  if(res.data.code == 0) {
			    this.friendLinkList = res.data.data.list
			  }
			})
		},
		friendlinkClick(url){
			if(url){
				window.open(url)
			}
		},
		newsIndexClick15(index) {
			this.newsIndex15 = index
			this.getNewsList()
		},
		getNewsCategory() {
			this.$http.get('newstype/list', {}).then(res => {
				if (res.data.code == 0) {
					this.newsCategoryList15 = res.data.data
				}
			});
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
		handleScroll() {
			let arr = [
				{id:'search',css:'animate__fadeInDown'},
				{id:'about',css:'animate__zoomInUp'},
				{id:'system',css:'animate__bounce'},
				{id:'animate_recommendyaopinxinxi',css:'animate__zoomIn'},
				{id:'animate_newsnews',css:'animate__zoomIn'},
				{id:'msgs',css:'animate__zoomInUp'},
				{id:'friendly',css:'animate__zoomInUp'}
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
	  //按药品信息查询
      search(tablename) {
        if (this.queryIndex == 0 && this.yaopinxinxiyaopinmingcheng) {
          this.$router.push({path: '/index/' + tablename, query: {indexQueryCondition: this.yaopinxinxiyaopinmingcheng}});
        }
      },
		getNewsList() {
			let data = {
				page: 1,
				limit: 7,
                sort: 'addtime',
				order: 'desc'
			}
			if(this.newsIndex15!=0) {
				data['typename'] = this.newsCategoryList15[this.newsIndex15 - 1].typename
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
				limit: 4,
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
		.search .select ::v-deep .el-input__inner {
			border: 0;
			border-radius: 4px;
			padding: 0 30px 0 10px;
			color: #00AA3A;
			width: 180px;
			font-size: 14px;
			height: 44px;
		}
		
		.search .input ::v-deep .el-input__inner {
			border: 10px;
			border-radius: 4px;
			padding: 0 10px;
			color: rgba(64, 158, 255, 1);
			width: 180px;
			font-size: 14px;
			height: 44px;
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
				transform: scale(0.96) translate3d(0px, 10px, 0px);
				transition: all 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
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
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
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
				transform: scale(0.96) translate3d(0px, 10px, 0px);
				transition: all 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
			}
		}
	}
	


	.home-preview .news .list15 .tab .item {
				cursor: pointer;
				border-radius: 6px;
				padding: 5px 10px;
				margin: 0 10px;
				color: #fff;
				display: flex;
				width: 20%;
				line-height: 44px;
				align-items: center;
			}
	
	.home-preview .news .list15 .tab .item:hover {
				color: #fff;
			}
	
	.home-preview .news .list15 .tab .item.active {
				color: #fff;
			}
	
	.home-preview .news .list15 .tab .more {
				cursor: pointer;
				border-radius: 6px;
				padding: 0 0 0 60px;
				margin: 0 10px;
				color: #fff;
				display: flex;
				line-height: 44px;
				align-items: center;
				position: relative;
			}
	
	.home-preview .news .list15 .tab .more:hover {
			}
	
	.home-preview .news .list15 .content .right .list-item  {
				border: 1px solid #efefef;
				cursor: pointer;
				padding: 0 84px 0 0 ;
				background: #fff;
				display: flex;
				width: 49%;
				justify-content: space-between;
				position: relative;
				height: auto;
			}
	.home-preview .news .list15 .content .right .list-item:hover  {
				border: 1px solid #c7f4d6;
				background: #c7f4d6;
			}
	
	.home-preview .news .list15 .content .right .list-item .date  {
				background-color: #00AA3A;
				padding: 12px 0 ;
				flex-direction: column;
				top: calc(50% - 32px);
				color: #fff;
				display: flex;
				width: 64px;
				justify-content: center;
				align-items: center;
				position: absolute;
				right: 10px;
				height: 64px;
			}
	.home-preview .news .list15 .content .right .list-item:hover .date {
				background-color: #fff;
			}
	.home-preview .news .list15 .content .right .list-item .date .day  {
				color: #fff;
				width: auto;
				font-size: 24px;
				line-height: 24px;
			}
	.home-preview .news .list15 .content .right .list-item:hover .date .day {
				color: #00AA3A;
				border-color: #fff;
			}
	.home-preview .news .list15 .content .right .list-item .date .year  {
				padding: 4px 0 0;
				color: #fff;
				width: auto;
				font-size: 14px;
			}
	.home-preview .news .list15 .content .right .list-item:hover .date .year {
				color: #00AA3A;
			}
	
	.home-preview .news .list15 .content .right .list-item .info .name  {
				padding: 20px 10px 12px;
				overflow: hidden;
				color: #000;
				white-space: nowrap;
				font-weight: 700;
				width: 100%;
				font-size: 18px;
				line-height: 26px;
				text-overflow: ellipsis;
			}
	.home-preview .news .list15 .content .right .list-item:hover .info .name {
			}
	
	.home-preview .news .list15 .content .right .list-item .info .desc  {
				padding: 0 10px;
				overflow: hidden;
				color: #666;
				white-space: nowrap;
				width: 100%;
				font-size: 14px;
				line-height: 20px;
				text-overflow: ellipsis;
			}
	.home-preview .news .list15 .content .right .list-item:hover .info .desc {
			}
	
	.home-preview .news .list15 .content .right .list-item .info .publisher .icon {
				margin: 0 2px 0 0;
				color: #666;
				font-size: 12px;
				line-height: 1.5;
			}
	.home-preview .news .list15 .content .right .list-item:hover .info .publisher .icon {
			}
	.home-preview .news .list15 .content .right .list-item .info .publisher .text {
				color: #666;
				font-size: 12px;
				line-height: 1.5;
			}
	.home-preview .news .list15 .content .right .list-item:hover .info .publisher .text {
			}
	
	.home-preview .news .list15 .content .right .list-item .info .like .icon {
				margin: 0 2px 0 0;
				color: #666;
				font-size: 12px;
				line-height: 1.5;
			}
	.home-preview .news .list15 .content .right .list-item:hover .info .like .icon {
			}
	.home-preview .news .list15 .content .right .list-item .info .like .text {
				color: #666;
				font-size: 12px;
				line-height: 1.5;
			}
	.home-preview .news .list15 .content .right .list-item:hover .info .like .text {
			}
	
	.home-preview .news .list15 .content .right .list-item .info .collect .icon {
				margin: 0 2px 0 0;
				color: #666;
				font-size: 12px;
				line-height: 1.5;
			}
	.home-preview .news .list15 .content .right .list-item:hover .info .collect .icon {
			}
	.home-preview .news .list15 .content .right .list-item .info .collect .text {
				color: #666;
				font-size: 12px;
				line-height: 1.5;
			}
	.home-preview .news .list15 .content .right .list-item:hover .info .collect .text {
			}
	
	.home-preview .news .list15 .content .right .list-item .info .view .icon {
				margin: 0 2px 0 0;
				color: #666;
				font-size: 12px;
				line-height: 1.5;
			}
	.home-preview .news .list15 .content .right .list-item:hover .info .view .icon {
			}
	.home-preview .news .list15 .content .right .list-item .info .view .text {
				color: #666;
				font-size: 12px;
				line-height: 1.5;
			}
	.home-preview .news .list15 .content .right .list-item:hover .info .view .text {
			}





	.home-preview .recommend .list12 .tab .item {
				cursor: pointer;
				border: 0;
				padding: 0 20px;
				margin: 0 10px;
				color: #333;
				font-weight: 600;
				display: flex;
				width: auto;
				font-size: 18px;
				line-height: 44px;
				align-items: center;
			}
	
	.home-preview .recommend .list12 .tab .item:hover {
				color: #00aa3a;
			}
	
	.home-preview .recommend .list12 .tab .item.active {
				color: #00aa3a;
				border-color: #999;
				border-width: 0 0 0px 0;
				border-style: solid;
			}
	
	.home-preview .recommend .list12 .tab .more {
				cursor: pointer;
				padding: 5px 10px;
				margin: 0 10px;
				color: #333;
				background: none;
				display: flex;
				line-height: 44px;
				align-items: center;
			}
	
	.home-preview .recommend .list12 .tab .more:hover {
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
