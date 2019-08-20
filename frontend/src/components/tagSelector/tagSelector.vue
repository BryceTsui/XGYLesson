<template>
  <div>
    <div v-bind:style="selectedStyle">
      <div  v-for="item2 in selectedLabelsList">
        <div class="item2">
          <span>{{item2}}</span>
          <el-button class="removeBtn" icon="el-icon-close" type="text" @click="remove(item2)"
                     style="color: white; font-size: 11px; position: absolute; right: 0px;top: -12px;"></el-button>
        </div>
      </div>
    </div>

    <div  v-bind:style="myStyle2">
      <span v-show="warning"  style="position:absolute; font-size: 12px;color: red; width: 200px; left:135px ">您最多可以选择{{maxNumOfLabels}}个标签</span>
      <span style="position: absolute; top: 0px;left: 2px; font-size: 12px;color: #ff8432; width: 200px; text-align: left">热门标签</span>
      <el-button icon="el-icon-refresh" type="text" class="refreshBtn" @click="refreshHotTag">换一批
      </el-button>
      <div  v-bind:style="myStyle">
        <div v-for="item in candidateLabelList">
          <div class="itemT" @click="select(item)">
            {{item}}
          </div>
        </div>
        <div style="float:left;">
          <div style="font-size: 12px;text-align: left">自定义标签</div>
          <div style="position: relative;margin-top: 3px">
            <input id="inputAdd" v-bind:style="inputStyle"  v-bind:placeholder="content" value=""/>
            <button class="addBtn" @click="addTag">添加</button>
          </div>
        </div>

      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: 'tagSelector',

  props: {

    refreshHotTag: {
      type: Function,
      default: function () {}
    },
    selectedDivWidth: {
      type: Number,
      default: 200
    },
    selectedDivHeight: {
      type: Number,
      default: 140
    },
    maxNumOfLabels: {
      type: Number,
      default: 15
    },
    placeHolder: {
      type: String,
      default: '简短扼要的标签可以起到更好的效果'
    },
    selectedLabelsList: {
      type: Array,
      default () {
        return []
      }
    },
    candidateLabelList: {
      type: Array,
      default () {
        return []
      }
    }
  },
  watch: {

    selectedDivWidth () {
      myStyle = 'width:' + this.selectedDivWidth + 'px;float:top;'
    },
    selectedDivHeight () {
      myStyle = 'width:' + this.selectedDivWidth + 'px;float:top;'
    }
  },
  mounted () {
    this.init()
  },
  data () {
    return {
      inputValue: '',
      inputStyle: 'font-size: 12px; width: ' + (this.selectedDivWidth - 60) +
        'px; height: 20px;border: 1px solid #ff8432;border-radius: 15px;padding-left: 10px;padding-right: 50px',
      content: this.placeHolder,
      warning: false,
      myStyle2: 'position:absolute; top: ' + this.selectedDivHeight + 'px;',
      myStyle: 'position:absolute; top: ' + 18 + 'px;width:' + this.selectedDivWidth + 'px;float:top;',
      selectedStyle: 'width:' + this.selectedDivWidth + 'px;height:' + this.selectedDivHeight + 'px;border: 1px solid #BBBBBB;',
      isCandidateShowList: []
    }
  },
  methods: {
    LTrim (str) {
      return str.replace(/^\s*/g, '')
    },
    // 去右空格
    RTrim (str) {
      return str.replace(/\s*$/g, '')
    },
    init () {
      for (var i = 0; i < this.candidateLabelList.length; i++) {
        this.isCandidateShowList.push(true)
      }
    },
    select (item) {
      if (this.selectedLabelsList.length >= this.maxNumOfLabels) {
        this.warning = true
        return
      }

      var has = false
      for (var i = 0; i < this.selectedLabelsList.length; i++) {
        if (this.selectedLabelsList[i] == item) {
          has = true
        }
      }
      if (has == false && this.selectedLabelsList.length <= this.maxNumOfLabels) { this.selectedLabelsList.push(item) }
    },
    remove (item) {
      var index = 0
      for (var i = 0; i < this.selectedLabelsList.length; i++) {
        if (this.selectedLabelsList[i] == item) {
          index = i
          break
        }
      }
      this.selectedLabelsList.splice(index, 1)
      this.warning = false
    },
    addTag () {
      if (this.selectedLabelsList.length >= this.maxNumOfLabels) {
        this.warning = true
        return
      }
      var has = false
      var item = document.getElementById('inputAdd').value
      item = this.LTrim(this.RTrim(item))
      if (item == '') return

      for (var i = 0; i < this.selectedLabelsList.length; i++) {
        if (this.selectedLabelsList[i] == item) {
          has = true
        }
      }
      if (has == false && this.selectedLabelsList.length <= this.maxNumOfLabels) { this.selectedLabelsList.push(item) }
      document.getElementById('inputAdd').value = ''
    }
  }
}

</script>

<style scoped>
  .itemT{
    font-size: 13px;
    color: #ff8432;
    margin: 2px;
    height: 20px;
    border: 1px solid #ff8432;
    border-radius: 15px;
    float: left;
    padding-left: 8px;
    padding-right: 8px;
    padding-top:2px;
  }
  .itemT:hover{
    color: white;
    background-color: #ff8432;
    cursor: default
  }
  .item2{
    position: relative;
    font-size: 13px;
    color: white;
    margin: 2px;
    height: 20px;
    background-color: #ff8432;
    border-radius: 5px;
    float: left;
    padding-left: 8px;
    padding-right: 10px;
    padding-top:4px;
  }
  .removeBtn{
    display: none;
  }
  .item2:hover{
    background-color: #ff732f;
    cursor: default
  }
  .item2:hover .removeBtn{
    display: block;
  }
  .addBtn{
    background-color: white;
    width: 50px;
    color: #ff8432;
    height: 23px;
    position: absolute;
    border: 1px solid #ff8432;
    border-radius: 15px;
    right: 0px;
  }
  .addBtn:hover{
    color: white;
    background-color: #ff8432;
  }
  .refreshBtn{
    position: absolute; top: -10px;left: 55px; font-size: 12px;color: #ff8432; width: 200px; text-align: left
  }
  .refreshBtn:hover{
    color: #ff7432;
    font-size: 13px;
  }
  .refreshBtn:active{
    //font-size: 18px;
    padding-left: 2px;
  }
</style>
