<template>
  <div>
  <div class="topic">
    <div class="left">
      <div class="title" v-html='topic.title' />
      <div class="info">

        <span>
          发布于&nbsp;
          {{$moment(topic.last_reply_at, 'YYYY-MM-DD')
          .startOf('day')
          .fromNow()}}
          &nbsp;•&nbsp;
        </span>
        作者：

        <a>
          {{topic.author_id}}
        </a>
        &nbsp;•&nbsp;

        <span>
          来自：
          {{$tab[topic.tab] && $tab[topic.tab].name}}
        </span>
      </div>
      <Divider />
      <div class="content" v-html='topic.content' />
      <Reply :data='topic.replies' />
    </div>
    <div class="right">
      <ProfilePanel :loginname='topic.author_id' />
      <OtherTopic />
      <RecentReply />
    </div>
  </div>

    <tinymce id="d2" v-model="replyContent"></tinymce>
    <div style="padding-left: 45%">
      <el-button type="success;" icon="el-icon-check" circle @click="submitReply" ></el-button>
    </div>

  </div>
</template>

<script>
  /**
   * 主题页
   */


  import Reply from "@/components/BBSReply";

  export default {
    /**
     * 给此组件起的名字，会显示在Vue的DevTool里面
     */
    name: "BBSTopic",
    props:["topic"],

    replyContent:'',
    /**
     * 当前组件的‘数据中心'
     */
    data() {
      return {
        topic: {}
      };
    },
    /**
     * 任何属于此组件的方法都定义在这里
     */
    created() {
      console.log('rrr')
      this.fetchData(1);
    },
    methods: {

      submitReply(){

        var xmlhttp = new XMLHttpRequest();
        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            _this.topic.replies.push(JSON.parse(xmlhttp.responseText))
          }
        }

        var reply={

          content:this.replyContent,
          username:localStorage.getItem("username"),
          topicid:this.topic.id,
        }

        xmlhttp.open('POST', 'http://localhost:8080/j2ee/bbs/create/reply', true)
        xmlhttp.setRequestHeader('Content-type', 'application/json; charset=utf-8')
        xmlhttp.send(JSON.stringify(reply))


      },
      /**
       * 封装好的获取数据的函数，这样就不需要每次使用都copy一遍代码了
       */
      fetchData(id) {

        var xmlhttp = new XMLHttpRequest();

        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

            console.log("kekeke")
            _this.topic = JSON.parse(xmlhttp.responseText)
            console.log("length"+_this.topic.replies.length)
          }
        }

        let formData = new FormData()
        formData.append('topicid', 1)
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/bbs/gettopic/detail', true)
        xmlhttp.send(formData)
         // this.topic =  {"id":"5bd4772a14e994202cd5bdb7","author_id":"504c28a2e2b845157708cb61","tab":"share","content":"<div class=\"markdown-text\"><p>2018年10月27日晚上，突然收到服务器不能访问的告警通知，拜托了狼叔 <a href=\"/user/i5ting\">@i5ting</a> 帮忙看看，结果登不上也ping不通。\n后来收到短信，发现是被ucloud封了，短信内容如下：</p>\n<blockquote>\n<p>【UCloud】尊敬的UCloud用户，您的IP：123.59.77.142  存在URL ：<a href=\"https://cnodejs.org/topic/57239bce5a26c4a841ecbf01\">https://cnodejs.org/topic/57239bce5a26c4a841ecbf01</a> （详细信息请查看邮箱）包含违禁内容（包括但不限于翻墙等），违反了国家有关法律法规。目前依主管单位要求，对您的IP予以封停，请您尽快处理违规内容。待处理完成后请联系技术支持重新开启业务。[4000188113]</p>\n</blockquote>\n<p>然后联系了ucloud的客服，一下就打通了，对方态度挺好处理问题也快。ucloud说是运营商那边封的，不是他们的检测机制。所以需要联系运营商解决。\n考虑到各位亲爱的网友们的行为我无法控制，那么一直跟越来越严格的审查系统对抗只会让自己疲惫，所以我就站点迁到国外。来到了aws jp。</p>\n<p>我大致测了测，电信和移动的访问速度非常快，100ms以内，联通会慢一点，400ms以内吧。</p>\n<p>建议翻墙访问。</p>\n</div>","title":"服务器迁移至 aws 日本机房","last_reply_at":"2019-02-21T08:14:21.958Z","good":false,"top":true,"reply_count":143,"visit_count":14780,"create_at":"2018-10-27T14:33:14.694Z","author":{"loginname":"alsotang","avatar_url":"https://avatars1.githubusercontent.com/u/1147375?v=4&s=120"},"replies":[{"id":"5bd47a0514e994202cd5bdbb","author":{"loginname":"zengxs","avatar_url":"https://avatars3.githubusercontent.com/u/18748098?v=4&s=120"},"content":"<div class=\"markdown-text\"><p>国内做论坛真不容易，我一开始还以为是被DDoS了</p>\n</div>","ups":["5875bd3d06fa6e2a4e4f730b","5b08bf1257137f22415c4671","5bdbcfa121d75b74609f3073","5856895b491e0754534fb0fd","5bdb193921d75b74609f2d40","5a40ca8e9807389a1809f6bf","5bc3efff15e4fd1923f48eee","5bd0f81b89665c365e94427c","5c174284f3d48d2397c11f64","5c10865bf3d48d2397c104eb","5b97a38937b3005a0b0e6e10","55d569b77bfbf9fc0f1ce339","59c896f5242810b428f50405","5aeed7891b02288048bd0ce8","5c6bb6dcb8913c5110b0c52e","5c507b9c3819b801aa5a29b2","5bbc5f5537a6965f59051fe9","5ae411231b02288048bd0add"],"create_at":"2018-10-27T14:45:25.900Z","reply_id":null,"is_uped":false}],"is_collect":false,"loginname":"alsotang","avatar_url":"https://avatars1.githubusercontent.com/u/1147375?v=4&s=120"};


      }
    },
    /**
     * 一般此钩子下面调用接口获取数据
     */

    /**
     * 在当前路由改变，但是该组件被复用时调用
     * 举例来说，对于一个带有动态参数的路径 /topic/:id，在 /topic/asdsd 和 /topic/sdfsdfa 之间跳转的时候，
     * 由于会渲染同样的 Topic 组件，因此组件实例会被复用。而这个钩子就会在这个情况下被调用。
     * 可以访问组件实例 `this`
     */
    beforeRouteUpdate(to, from, next) {
      //this.fetchData(1);
     // next();
    },
    /**
     * 注册引用进来的其他组件
     */
    components: {
      Reply
    }
  };
</script>

<style lang="scss" scoped>
  $grey-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

  .topic {

    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
    "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: rgba(0, 0, 0, 0.65);
    font-size: 14px;
    text-align: left;
    display: flex;
    margin-bottom: 100px;
    .title {
      color: black;
      font-weight: bold;
      font-size: 25px;
      margin-bottom: 8px;
    }
    .content {
      color: #333;
      line-height: 1.6;
      margin-bottom: 50px;
      /deep/ img {
        width: 100%;
      }

    }
    .left {
      width: 72%;
      float: left;
      box-shadow: $grey-shadow;
      padding: 20px;
      .info {
        display: flex;
        align-items: center;
        color: #838383;
        font-size: 12px;
      }
    }
    .right {
      float: right;
      width: 25%;
      height: 100%;
      margin-left: 2%;
    }
  }

  a {
    color: #42b983;
    font-weight: 600;
    padding: 0px 2px;
    text-decoration: none;
  }
</style>

