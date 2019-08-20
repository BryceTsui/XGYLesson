<template>
  <div>
    <div style="float: top; left: 0px;top: 0px; width: 100%; height: 700px;">
      <div>
        <img id="backImg" src="../assets/home_back.png" style="position: absolute;top: 0px;left: 0px;width:100%">
      </div>
      <img src="./logo.png" style="width: 150px;position: absolute;top: 25px;left: 100px;">
      <span style="position: absolute; top: 40px; left: 270px;color: #f2f2f2; font-size: 28px">众包标注系统</span>
      <span style="position: absolute; top: 200px; left: 50%;margin-left:-290px;color: #f2f2f2; font-size: 48px">准确、高效的众包标注系统</span>
    </div>
    <div style="float: top; left: 0px;top: 650px; height: 460px;width: 100%;">
      <div style="position: relative;">
        <div style="position: absolute; width: 5px; height:30px; background-color:#108cee; left: 30px; top: 10px"></div>
        <span style="position: absolute; left: 40px;font-size: 22px; font-weight: bold;top: 10px">产品优势</span>
        <div style="position: absolute;width:600px; height: 200px;top: 40px;left: 10px;">
          <img src="../assets/jifen.png" style="position: absolute; width: 140px; left: 45px; top:32px;">
          <span style="position: absolute; font-size: 20px;left: 210px;top:35px;">高效的积分激励系统</span>
          <span style="position: absolute; font-size: 14px;left: 210px;top:75px;">使用积分作为发布者和工作者之间的交易渠道，发布者可以</span>
          <span style="position: absolute; font-size: 14px;left: 210px;top:100px;">通过提高积分来吸引工作者，从而更有效率地完成项目。积</span>
          <span style="position: absolute; font-size: 14px;left: 210px;top:125px;">分可以充值或提现。</span>
        </div>
        <div style="position: absolute;width:600px; height: 200px;top: 40px;left: 630px;">
          <img src="../assets/chart_home.png" style="position: absolute; width: 200px; left: 50px; top:30px;">
          <span style="position: absolute; font-size: 20px;left: 210px;top:35px;">统计数据图表化</span>
          <span style="position: absolute; font-size: 14px;left: 210px;top:75px;">项目截止后，系统会统计和分析所有的数据，并且以图表</span>
          <span style="position: absolute; font-size: 14px;left: 210px;top:100px;">的形式展示出来，包括饼图、柱状图、折线图等多种形式。</span>
          <span style="position: absolute; font-size: 14px;left: 210px;top:125px;">您可以非常清晰地看到统计结果。</span>
        </div>
        <div style="position: absolute;width:600px; height: 200px;top: 220px;left: 10px;">
          <img src="../assets/liucheng_home.png" style="position: absolute; width: 150px; left: 60px; top:0px;">
          <span style="position: absolute; font-size: 20px;left: 210px;top:35px;">专业的标注流程</span>
          <span style="position: absolute; font-size: 14px;left: 210px;top:75px;">简单易懂易上手的工作台，专业合理的作业流。工作者能</span>
          <span style="position: absolute; font-size: 14px;left: 210px;top:100px;">够更高效快速地完成工作，并且可以随时查看工作进度。</span>
          <span style="position: absolute; font-size: 14px;left: 210px;top:125px;"></span>
        </div>
      </div>
    </div>
    <div style="float: top; left: 0px;top: 1100px;width: 100%; height: 680px">
      <div style="position: relative;">
        <div style="position: absolute; width: 5px; height:30px; background-color:#108cee; left: 30px;"></div>
        <span style="position: absolute; left: 40px;font-size: 22px; font-weight: bold">产品流程</span>
        <img src="../assets/liucheng.png" style="position: absolute; left: 15%; top: 40px; width: 70%">
      </div>
    </div>
    <div style="float: top;height:1400px; width: 1260px">
      <div style="position: relative;">
        <div style="position: absolute; width: 5px; height:30px; background-color:#108cee; left: 30px;"></div>
        <span style="position: absolute; left: 40px;font-size: 22px; font-weight: bold">标注详情</span>
        <div style="position: absolute; width: 1260px;top: 50px;left: 50%; margin-left: -580px">
          <div style="position: absolute; width: 600px; height: 400px; top: 50px;">
            <img src="../assets/fangkuang_home.jpg" style="width: 500px;">
            <span style="position: absolute; top: 290px; font-size: 32px; width: 400px; left:90px; color: #02cc77">方框标注(Detection)</span>
            <p style="position:absolute;top: 330px;left:100px;font-size: 20px">根据标注需求，对图像中的目标物体进行画框</p>
          </div>
          <div style="position: absolute; width: 600px; height: 400px; top: 50px;left: 560px;">
            <img src="../assets/quyu_home.jpg" style="width: 500px;">
            <span style="position: absolute; top: 290px; font-size: 32px; width: 400px; left:90px; color: #02cc77">区域标注(Segmentation)</span>
            <p style="position:absolute;top: 330px;left:60px;font-size: 20px">根据标注需求，使⽤连续的折线标注图像中目标物体的</p>
            <p style="position:absolute;top: 360px;left:60px;font-size: 20px">轮廓，返回所有轮廓组成点的坐标。</p>
          </div>
          <div style="position: absolute; width: 600px; height: 400px; top: 500px;left: 0px;">
            <img src="../assets/caption.png" style="width: 500px;">
            <span style="position: absolute; top: 290px; font-size: 32px; width: 400px; left:90px; color: #02cc77">整体描述(Caption)</span>
            <p style="position:absolute;top: 330px;left:60px;font-size: 20px">针对图像中具体情景及标注需求，为每张图像生成中文</p>
            <p style="position:absolute;top: 360px;left:60px;font-size: 20px">标注语句。</p>
          </div>
          <div style="position: absolute; width: 600px; height: 400px; top: 500px;left: 560px;">
            <img src="../assets/classification.jpg" style="width: 500px;">
            <span style="position: absolute; top: 290px; font-size: 32px; width: 400px; left:90px; color: #02cc77">图像分类(Classification)</span>
            <p style="position:absolute;top: 330px;left:100px;font-size: 20px">根据图像内容，选择给出选项中合适的一项。</p>
          </div>
          <div style="position: absolute; width: 600px; height: 400px; top: 950px;left: 0px;">
            <img src="../assets/attribute.jpg" style="width: 500px;">
            <span style="position: absolute; top: 290px; font-size: 32px; width: 400px; left:90px; color: #02cc77">属性标注(Attribute)</span>
            <p style="position:absolute;top: 330px;left:100px;font-size: 20px">根据图像内容和给定的属性，填写属性值。</p>
          </div>
        </div>

      </div>
    </div>
    <div style="position:absolute;left:0px;float: top;height:150px; width: 100%;background-color:#24292E;">
      <div style="position:relative;">
        <span style="position: absolute; top: 10px; left: 100px; color: white;font-size: 18px">COUNTS_FX众筹标注系统</span>
        <span style="position: absolute; top: 50px; left: 100px; color: white">开发者：FX开发组</span>
        <span style="position: absolute; top: 50px; left: 320px; color: white">联系电话：188XXXX6150</span>
        <span style="position: absolute; top: 50px; left: 600px; color: white">邮箱：counts_fx@163.com</span>
      </div>
    </div>
    <img src="../assets/bottom.png" style="width: 100%;position: fixed; bottom:0px;left: 0px;">
    <div style="position: fixed;left: 0px;bottom: 0px;width: 100%; height: 45px;">
      <el-button type="text" class="login"  @click="login">登陆</el-button>
      <el-button type="text" class="login"  @click="register">注册</el-button>
      <el-button type="text" class="login" @click="goMarket">众包市场</el-button>
    </div>
  </div>

</template>

<script>

export default {

  name: 'home',
  data () {
    return {}
  }
}
</script>

<style scoped>
  .login {
    color: #fdfdfd;
    -moz-opacity: 1.4;
    opacity: 1.4;
    font-size: 16px;
  }
  .login:hover{
    text-decoration: underline;
  }
</style>
