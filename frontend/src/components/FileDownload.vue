<template>
  <div style="top: 50px; left: 0px; padding-left: 20px;background-color: white;">
      <div v-for="item in data" style="float: left" :key="item">
        <el-card  class="el_card" :body-style="{padding:'0px'}" >

          <div class="file_icon" @click="downloadFile(item.name)">
          <img :src="img_index[item.type]"  class="image" v-bind:id="item.id">
          </div>
          <div class = "file_name">
            <a :href="'http://localhost:8080/j2ee/file/download?courseid='+courseid+'&resoursename='+item.name" >
          <span>{{item.name}}</span>
            </a>
          </div>
          <div class="imgOnClick">
          </div>

        </el-card>
      </div>
    </div>
</template>


<script>
  import JPG from "@/images/file_img/JPGtubiao.png"

  import DOC from "@/images/file_img/DOCtubiao.png"
  import EXE from "@/images/file_img/EXEtubiao.png"
  import GIF from "@/images/file_img/GIFtubiao.png"
  import HTML from "@/images/file_img/HTMLtubiao.png"
  import MP from "@/images/file_img/MPtubiao.png"
  import PDF from "@/images/file_img/PDFtubiao.png"
  import PNG from "@/images/file_img/PNGtubiao.png"
  import PPT from "@/images/file_img/PPTtubiao.png"
  import SVG from "@/images/file_img/SVGtubiao.png"
  import TXT from "@/images/file_img/TXTtubiao.png"
  import XSL from "@/images/file_img/XSLtubiao.png"
  import ZIP from "@/images/file_img/ZIPtubiao.png"


  export default {
    name:"FileDownload",
    props:["data","courseid"],
    data() {
      return {

        img_index: {

          "JPG": JPG,
          "JPNG":JPG,
          "DOCX":DOC,
          "CSV":XSL,
          "DOC":DOC,
          "EXE":EXE,
          "GIF":GIF,
          "HTML":HTML,
          "MP":MP,
          "PDF":PDF,
          "PNG":PNG,
          "PPT":PPT,
          "PPTX":PPT,
          "SVG":SVG,
          "TXT":TXT,
          "XSL":XSL,
          "ZIP":ZIP,

        },
        projectInfo: [
          {
            show:true,
            id: '01',
            missionname: 'miss',
            dateStart: '2011-1-2',
            dateEnd:'2016-3-2',
            charts_id: '1_charts',
            imgFinished: 17,
            imgToDo: 3,
            type: 'Caption',
            cover: '',
            annotationType: 0,
            counts: 5,
            percent:0,
            isEnd:false,
            isContinue:true
          },
          {
            show:true,
            id: '01',
            missionname: 'miss',
            dateStart: '2011-1-2',
            dateEnd:'2016-3-2',
            charts_id: '1_charts',
            imgFinished: 17,
            imgToDo: 3,
            type: 'Caption',
            cover: '',
            annotationType: 0,
            counts: 5,
            percent:0,
            isEnd:false,
            isContinue:true
          },
          {
            show:true,
            id: '01',
            missionname: 'miss',
            dateStart: '2011-1-2',
            dateEnd:'2016-3-2',
            charts_id: '1_charts',
            imgFinished: 17,
            imgToDo: 3,
            type: 'Caption',
            cover: '',
            annotationType: 0,
            counts: 5,
            percent:0,
            isEnd:false,
            isContinue:true
          },
          {
            show:true,
            id: '01',
            missionname: 'miss',
            dateStart: '2011-1-2',
            dateEnd:'2016-3-2',
            charts_id: '1_charts',
            imgFinished: 17,
            imgToDo: 3,
            type: 'Caption',
            cover: '',
            annotationType: 0,
            counts: 5,
            percent:0,
            isEnd:false,
            isContinue:true
          },
          {
            show:true,
            id: '01',
            missionname: 'miss',
            dateStart: '2011-1-2',
            dateEnd:'2016-3-2',
            charts_id: '1_charts',
            imgFinished: 17,
            imgToDo: 3,
            type: 'Caption',
            cover: '',
            annotationType: 0,
            counts: 5,
            percent:0,
            isEnd:false,
            isContinue:true
          },
          {
            show:true,
            id: '01',
            missionname: 'miss',
            dateStart: '2011-1-2',
            dateEnd:'2016-3-2',
            charts_id: '1_charts',
            imgFinished: 17,
            imgToDo: 3,
            type: 'Caption',
            cover: '',
            annotationType: 0,
            counts: 5,
            percent:0,
            isEnd:false,
            isContinue:true
          },
          {
            show:true,
            id: '01',
            missionname: 'miss',
            dateStart: '2011-1-2',
            dateEnd:'2016-3-2',
            charts_id: '1_charts',
            imgFinished: 17,
            imgToDo: 3,
            type: 'Caption',
            cover: '',
            annotationType: 0,
            counts: 5,
            percent:0,
            isEnd:false,
            isContinue:true
          },
          {
            show:true,
            id: '01',
            missionname: 'miss',
            dateStart: '2011-1-2',
            dateEnd:'2016-3-2',
            charts_id: '1_charts',
            imgFinished: 17,
            imgToDo: 3,
            type: 'Caption',
            cover: '',
            annotationType: 0,
            counts: 5,
            percent:0,
            isEnd:false,
            isContinue:true
          },
          {
            show:true,
            id: '01',
            missionname: 'miss',
            dateStart: '2011-1-2',
            dateEnd:'2016-3-2',
            charts_id: '1_charts',
            imgFinished: 17,
            imgToDo: 3,
            type: 'Caption',
            cover: '',
            annotationType: 0,
            counts: 5,
            percent:0,
            isEnd:false,
            isContinue:true
          },
        ],
      }


      },

    methods:{
      downloadFile:function(value){

        console.log("123"+value)
        var xmlhttp = new XMLHttpRequest();
        var _this = this
        xmlhttp.onreadystatechange = function () {
          if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

          }
        }

        console.log("1234")
        let formData = new FormData()
        console.log(value)
        formData.append('courseid', 1)
        formData.append('resoursename','test3.jpg')
        xmlhttp.open('POST', 'http://localhost:8080/j2ee/file/download', true)
        xmlhttp.send(formData)


      }
  }
  }
</script>

<style scoped>

  .file_icon{
    width: 60px;
    height: 70px;
    padding-left: 10px;
  }
  .imgOnClick {
    width: 100%;
    height: 60%;
    filter: alpha(Opacity=50);
    -moz-opacity: 0.6;
    opacity: 0.6;
    z-index: 100;
    background-color: #ffffff;
    display: none;
    position: absolute;
    left: 0px;
    top:5px;
  }
.file_name{
  white-space: nowrap;
  text-overflow:ellipsis;
    overflow:hidden;
  font-size:15px;
  width: 100%;
}
  .el_card{
    margin: 10px;
    padding: 5px;
    width: 80px;
    height:100px;
    float: left;
    position: relative;
  }
  .el_card:hover .imgOnClick{
    display:block;
  }
  .el_card:hover{

  }
  .time{
    left: 10px;
    font-size: 14px;
    color: #999;
  }
  .card_button{
    padding: 0px;
    float: right;
  }
  .image{
    width: 100%;
    display: block;
  }
  * {
    margin: 0;
    padding: 0;
  }

  .btn {
    display: inline-block;
    line-height: 1;
    white-space: nowrap;
    cursor: pointer;
    background: #fff;
    border: 1px solid #c0ccda;
    color: #1f2d3d;
    text-align: center;
    box-sizing: border-box;
    outline: none;
    margin:20px 10px 0px 0px;
    padding: 9px 15px;
    font-size: 14px;
    border-radius: 4px;
    color: #fff;
    background-color: #50bfff;
    border-color: #50bfff;
    transition: all .2s ease;
    text-decoration: none;
    user-select: none;
  }

  .show-info {
    margin-bottom: 50px;
  }

  .test {
    height: 300px;
    width: 400px;
  }

  .model img {
    display: block;
    margin: auto;
    max-width: 80%;
    user-select: none;
    background-position: 0px 0px, 10px 10px;
    background-size: 20px 20px;
    background-image: linear-gradient(45deg, #eee 25%, transparent 25%, transparent 75%, #eee 75%, #eee 100%),linear-gradient(45deg, #eee 25%, white 25%, white 75%, #eee 75%, #eee 100%);
  }

  @keyframes slide {
    0%  {
      background-position: 0 0;
    }
    100% {
      background-position: -100% 0;
    }
  }

  #edit_name{
    display: none;
  }

  #account_info:hover #edit_name{
    display: block;
  }
  #edit_details{
    display: none;
  }

  #details_info:hover #edit_details{
    display: block;
  }
  #edit_connect{
    display: none;
  }

  #connect_info:hover #edit_connect{
    display: block;
  }
  .labelType{
    width:100px;
    padding-top: 3px;
    height: 24px;
    border-radius: 15px;
    color: white;
    background-color: #2ea2ff;
    font-size: 13px;
    cursor: pointer;
  }
  .labelType:hover{
    color:white;
    cursor: pointer;
  }
</style>

