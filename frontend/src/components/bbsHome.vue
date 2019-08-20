<template>
  <div>
  <Header v-on:gotoBBSHome="gotoBBSHome"></Header>
  <div class="home" v-show="homeShow">
    <el-tabs v-model="tab" @tab-click="tabChanged">
      <el-tab-pane label="全部" name="all">
        <Content :list='list' v-on:gotoDetail="gotoDetail"/>
      </el-tab-pane>
      <el-tab-pane label="精华" name="good">
        <Content :list='list' v-on:gotoDetail="gotoDetail"/>
      </el-tab-pane>
      <el-tab-pane label="分享" name="share">
        <Content :list='list' v-on:gotoDetail="gotoDetail"/>
      </el-tab-pane>
      <el-tab-pane label="问答" name="ask">
        <Content :list='list' v-on:gotoDetail="gotoDetail"/>
      </el-tab-pane>
      <el-tab-pane label="实验" name="exp">
        <Content :list='list' v-on:gotoDetail="gotoDetail" />
      </el-tab-pane>
    </el-tabs>

    <div style="height: 50px;">
      <span style="font-size: x-large;padding-left: 40% ">发布主题帖</span>
    </div>

    <div >

      <el-input v-model="topicName" placeholder="请输入主题名" style="width: 80%;padding-left: 10%"></el-input>

      <div style="margin-top: 5px;margin-bottom: 5px;padding-left: 15%">
        <el-radio v-model="radio8" label="share" border size="medium">分享</el-radio>
        <el-radio v-model="radio8" label="good" border size="medium">精华</el-radio>
        <el-radio v-model="radio8" label="ask" border size="medium">问答</el-radio>
        <el-radio v-model="radio8" label="exp" border size="medium">实验</el-radio>
      </div>
    </div>

    <tinymce id="d1" v-model="htmlContent"></tinymce>
    <div style="padding-left: 45%">
      <el-button type="success;" icon="el-icon-check" circle @click="submitTopic" ></el-button>
    </div>

  </div>
    <div v-show="topicShow">
      <BBSTopic :topic='topic'></BBSTopic>
    </div>

  </div>
</template>

<script>
  /**
   * 主页
   */
  import Content from "@/components/BBSContent.vue";
  import Header from "@/components/BBSHeader.vue";
  import BBSTopic from "@/components/BBSTopic"

  export default {
    /**
     * 给此组件起的名字，会显示在Vue的DevTool里面
     */
    name: "BBSHome",
    /**
     * 当前组件的‘数据中心'
     */
    props:["list","topic"],
    data() {
      return {

        chooseTopic:'',
        radio8: 'share',


        topic:{},
        topicName:'',
        htmlContent:'',

        homeShow:true,
        topicShow:false,
        page: 1,
        limit: 20,
        tab: "all",
        list:[],
        store: {} // 存储所有Tab对应的数据，因为切换Tab后就没必要重新以limit:20加载数据。
      };
    },

    /**
     * 任何属于此组件的方法都定义在这里
     */
    methods: {


      submitTopic(){

        var topic ={
          description:this.htmlContent,
          username:localStorage.getItem("username"),
          type:this.radio8,
          title:this.topicName,
          courseid:localStorage.getItem("courseid")
        }

        var xmlhttp = new XMLHttpRequest()
        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

          }
        }

        xmlhttp.open('POST', 'http://localhost:8080/j2ee/bbs/create/topic', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
        xmlhttp.send(JSON.stringify(topic))
      },
      gotoDetail:function(value){

        var xmlhttp = new XMLHttpRequest();

        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            _this.topic = JSON.parse(xmlhttp.responseText)
            _this.homeShow=false;
            _this.topicShow= true;
          }
        }

        let formData = new FormData()
        formData.append('topicid', value)
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/bbs/gettopic/detail', true)
        xmlhttp.send(formData)
        // this.topic =  {"id":"5bd4772a14e994202cd5bdb7","author_id":"504c28a2e2b845157708cb61","tab":"share","content":"<div class=\"markdown-text\"><p>2018年10月27日晚上，突然收到服务器不能访问的告警通知，拜托了狼叔 <a href=\"/user/i5ting\">@i5ting</a> 帮忙看看，结果登不上也ping不通。\n后来收到短信，发现是被ucloud封了，短信内容如下：</p>\n<blockquote>\n<p>【UCloud】尊敬的UCloud用户，您的IP：123.59.77.142  存在URL ：<a href=\"https://cnodejs.org/topic/57239bce5a26c4a841ecbf01\">https://cnodejs.org/topic/57239bce5a26c4a841ecbf01</a> （详细信息请查看邮箱）包含违禁内容（包括但不限于翻墙等），违反了国家有关法律法规。目前依主管单位要求，对您的IP予以封停，请您尽快处理违规内容。待处理完成后请联系技术支持重新开启业务。[4000188113]</p>\n</blockquote>\n<p>然后联系了ucloud的客服，一下就打通了，对方态度挺好处理问题也快。ucloud说是运营商那边封的，不是他们的检测机制。所以需要联系运营商解决。\n考虑到各位亲爱的网友们的行为我无法控制，那么一直跟越来越严格的审查系统对抗只会让自己疲惫，所以我就站点迁到国外。来到了aws jp。</p>\n<p>我大致测了测，电信和移动的访问速度非常快，100ms以内，联通会慢一点，400ms以内吧。</p>\n<p>建议翻墙访问。</p>\n</div>","title":"服务器迁移至 aws 日本机房","last_reply_at":"2019-02-21T08:14:21.958Z","good":false,"top":true,"reply_count":143,"visit_count":14780,"create_at":"2018-10-27T14:33:14.694Z","author":{"loginname":"alsotang","avatar_url":"https://avatars1.githubusercontent.com/u/1147375?v=4&s=120"},"replies":[{"id":"5bd47a0514e994202cd5bdbb","author":{"loginname":"zengxs","avatar_url":"https://avatars3.githubusercontent.com/u/18748098?v=4&s=120"},"content":"<div class=\"markdown-text\"><p>国内做论坛真不容易，我一开始还以为是被DDoS了</p>\n</div>","ups":["5875bd3d06fa6e2a4e4f730b","5b08bf1257137f22415c4671","5bdbcfa121d75b74609f3073","5856895b491e0754534fb0fd","5bdb193921d75b74609f2d40","5a40ca8e9807389a1809f6bf","5bc3efff15e4fd1923f48eee","5bd0f81b89665c365e94427c","5c174284f3d48d2397c11f64","5c10865bf3d48d2397c104eb","5b97a38937b3005a0b0e6e10","55d569b77bfbf9fc0f1ce339","59c896f5242810b428f50405","5aeed7891b02288048bd0ce8","5c6bb6dcb8913c5110b0c52e","5c507b9c3819b801aa5a29b2","5bbc5f5537a6965f59051fe9","5ae411231b02288048bd0add"],"create_at":"2018-10-27T14:45:25.900Z","reply_id":null,"is_uped":false}],"is_collect":false,"loginname":"alsotang","avatar_url":"https://avatars1.githubusercontent.com/u/1147375?v=4&s=120"};


        this.chooseTopic = value;



      },
      gotoBBSHome(){
        this.homeShow=true;
        this.topicShow= false;
      },
      /**
       * 封装好的获取首页数据的函数，这样就不需要每次使用都copy一遍代码了
       */
      getTopics() {
        // getTopics({
        //   page: this.page,
        //   limit: this.limit,
        //   tab: this.tab
        // }).then(res => {
        //   this.list = [ {"id":"5bd4772a14e994202cd5bdb7","author_id":"504c28a2e2b845157708cb61","tab":"good","content":"<div class=\"markdown-text\"><p>2018年10月27日晚上，突然收到服务器不能访问的告警通知，拜托了狼叔 <a href=\"/user/i5ting\">@i5ting</a> 帮忙看看，结果登不上也ping不通。\n后来收到短信，发现是被ucloud封了，短信内容如下：</p>\n<blockquote>\n<p>【UCloud】尊敬的UCloud用户，您的IP：123.59.77.142  存在URL ：<a href=\"https://cnodejs.org/topic/57239bce5a26c4a841ecbf01\">https://cnodejs.org/topic/57239bce5a26c4a841ecbf01</a> （详细信息请查看邮箱）包含违禁内容（包括但不限于翻墙等），违反了国家有关法律法规。目前依主管单位要求，对您的IP予以封停，请您尽快处理违规内容。待处理完成后请联系技术支持重新开启业务。[4000188113]</p>\n</blockquote>\n<p>然后联系了ucloud的客服，一下就打通了，对方态度挺好处理问题也快。ucloud说是运营商那边封的，不是他们的检测机制。所以需要联系运营商解决。\n考虑到各位亲爱的网友们的行为我无法控制，那么一直跟越来越严格的审查系统对抗只会让自己疲惫，所以我就站点迁到国外。来到了aws jp。</p>\n<p>我大致测了测，电信和移动的访问速度非常快，100ms以内，联通会慢一点，400ms以内吧。</p>\n<p>建议翻墙访问。</p>\n</div>","title":"服务器迁移至 aws 日本机房","last_reply_at":"2019-02-21T03:59:42.640Z","good":false,"top":true,"reply_count":142,"visit_count":14735,"create_at":"2018-10-27T14:33:14.694Z","author":{"loginname":"alsotang","avatar_url":"https://avatars1.githubusercontent.com/u/1147375?v=4&s=120"}}];
        //
        //   console.log( JSON.stringify(this.list[0]));
        //   this.limit = this.limit + 10;
        //
        //   const store = this.store;
        //   // 将数据存储到对应的key下
        //   store[this.tab] = {
        //     limit: this.limit,
        //     data: res.data
        //   };
        // });
      },
      /**
       * 滚动函数，判断当前滚动条是否到了底部来决定是否重新拉取数据
       */
      scrollMethod() {
        const sumH =
          document.body.scrollHeight || document.documentElement.scrollHeight;
        const viewH = document.documentElement.clientHeight;
        const scrollH =
          document.body.scrollTop || document.documentElement.scrollTop;
        if (viewH + scrollH >= sumH) {
          this.getTopics();
        }
      },
      /**
       * 当前Tab变化时判断Store里是否已经存储数据。
       * 是：拉出来，设置到state中
       * 否：重新获取数据
       */
      tabChanged() {
        var xmlhttp = new XMLHttpRequest();

        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            _this.list = JSON.parse(xmlhttp.responseText)
          }
        }

        let formData = new FormData()
        formData.append('courseid', localStorage.getItem("courseid"))
        formData.append('type', this.tab)
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/bbs/gettopicvo', true)
        xmlhttp.send(formData)
        //this.limit = store[this.tab].limit;
      }
    },
    /**
     * 一般此钩子下面调用接口获取数据
     */
     created() {

     },
    /**
     * 组件被销毁的钩子：移除绑定的滚动事件
     */
    destroyed() {
      window.removeEventListener("scroll", this.scrollMethod);
    },
    /**
     * 注册引用进来的其他组件
     */
    components: {
      Header,
      Content,
      BBSTopic,
    }
  };
</script>

<style lang="scss" scoped>
  .home {
    margin: auto;
    width: 100%;
    padding: 20px 30px;
    box-sizing: border-box;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  }
</style>

