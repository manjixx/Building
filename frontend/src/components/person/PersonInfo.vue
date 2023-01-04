<template>
  <div class="container">
    <div class="details-bg">
      <h3 class="form_header" >个人基本信息</h3>
      <el-form :model="infoForm" :rules="infoRules" ref="infoForm" label-width="80px" class="block_form">
        <el-row :gutter=24>
          <el-col :span=12 >
            <el-form-item label="姓名" prop="name" >
              <el-input v-model="infoForm.name"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span=12>
            <el-form-item label="性别" prop="gender" style="width:max-content">
              <el-select v-model="infoForm.gender" style="width:268px">
                <el-option label="男" value="1"></el-option>
                <el-option label="女" value="0"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter=24 style="margin-top: 25px">
          <el-col :span=12>
            <el-form-item label="年龄" prop="age">
              <el-input v-model.number="infoForm.age"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="城市" prop="region">
              <el-input v-model="infoForm.region"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="details-bg">
      <h3 class="form_header">热环境与热舒适影响因素的主观评价</h3>
      <el-form :model="infoForm" :rules="infoRules" ref="infoForm" class="block_form">
        <el-row>
          <el-col :span="24" class="topic_form">1.  请选择对日常工作环境的主观偏好</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="pref">
              <el-radio-group v-model="infoForm.pref" class = el-radio_label>
                <el-radio :label="-1" >偏凉爽</el-radio>
                <el-radio :label="0" >中性</el-radio>
                <el-radio :label="1" >偏温暖</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24" class="topic_form">2. 请选择对日常工作环境热变化的敏感程度</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="sens">
              <el-radio-group v-model="infoForm.sens" class = el-radio_label>
                <el-radio :label="-1" >不敏感</el-radio>
                <el-radio :label="0" >轻度敏感</el-radio>
                <el-radio :label="1" >非常敏感</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24" class="topic_form">3. 请选择对实验环境的主观评价/印象</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="eval">
              <el-radio-group v-model="infoForm.eval" class = el-radio_label>
                <el-radio :label="-1" >偏凉爽</el-radio>
                <el-radio :label="0" >不冷不热</el-radio>
                <el-radio :label="1" >偏温暖</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24" class="topic_form">4. 请选择对"室内温度对你的热体验/热舒适性影响非常大"这一命题认同的认同程度</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="impInTmp">
              <el-radio-group v-model="infoForm.impInTmp" class = el-radio_label>
                <el-radio :label="5" >非常认同</el-radio>
                <el-radio :label="4" >认同</el-radio>
                <el-radio :label="3" >无所谓</el-radio>
                <el-radio :label="2" >不认同</el-radio>
                <el-radio :label="1" >非常不认同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24" class="topic_form">5. 请选择对"室内湿度对你的热体验/热舒适性影响非常大"这一命题认同的认同程度</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="impInRh">
              <el-radio-group v-model="infoForm.impInRh" class = el-radio_label>
                <el-radio :label="5" >非常认同</el-radio>
                <el-radio :label="4" >认同</el-radio>
                <el-radio :label="3" >无所谓</el-radio>
                <el-radio :label="2" >不认同</el-radio>
                <el-radio :label="1" >非常不认同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24" class="topic_form">6. 请选择对"室内空气质量对你的热体验/热舒适性影响非常大"这一命题认同的认同程度</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="impInAQ">
              <el-radio-group v-model="infoForm.impInAQ" class = el-radio_label>
                <el-radio :label="5" >非常认同</el-radio>
                <el-radio :label="4" >认同</el-radio>
                <el-radio :label="3" >无所谓</el-radio>
                <el-radio :label="2" >不认同</el-radio>
                <el-radio :label="1" >非常不认同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24" class="topic_form">7. 请选择对"室内空气流速对你的热体验/热舒适性影响非常大"这一命题认同的认同程度</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="impInAV">
              <el-radio-group v-model="infoForm.impInAV" class = el-radio_label>
                <el-radio :label="5" >非常认同</el-radio>
                <el-radio :label="4" >认同</el-radio>
                <el-radio :label="3" >无所谓</el-radio>
                <el-radio :label="2" >不认同</el-radio>
                <el-radio :label="1" >非常不认同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24" class="topic_form">8. 请选择对"天气（晴、阴，多云等）对你的热体验/热舒适性影响非常大"这一命题的认同程度</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="impWW">
              <el-radio-group v-model="infoForm.impWW" class = el-radio_label>
                <el-radio :label="5" >非常认同</el-radio>
                <el-radio :label="4" >认同</el-radio>
                <el-radio :label="3" >无所谓</el-radio>
                <el-radio :label="2" >不认同</el-radio>
                <el-radio :label="1" >非常不认同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24" class="topic_form">9. 请选择对"室外温度对你的热体验/热舒适性影响非常大"这一命题认同的认同程度</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="impOTmp">
              <el-radio-group v-model="infoForm.impOTmp" class = el-radio_label>
                <el-radio :label="5" >非常认同</el-radio>
                <el-radio :label="4" >认同</el-radio>
                <el-radio :label="3" >无所谓</el-radio>
                <el-radio :label="2" >不认同</el-radio>
                <el-radio :label="1" >非常不认同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24" class="topic_form">10. 请选择对"室外温度对你的热体验/热舒适性影响非常大"这一命题认同的认同程度</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="impORh">
              <el-radio-group v-model="infoForm.impORh" class = el-radio_label>
                <el-radio :label="5" >非常认同</el-radio>
                <el-radio :label="4" >认同</el-radio>
                <el-radio :label="3" >无所谓</el-radio>
                <el-radio :label="2" >不认同</el-radio>
                <el-radio :label="1" >非常不认同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24" class="topic_form">11. 请选择对"心情对你的热体验/热舒适性影响非常大"这一命题的认同程度。</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="impMood">
              <el-radio-group v-model="infoForm.impMood" class = el-radio_label>
                <el-radio :label="5" >非常认同</el-radio>
                <el-radio :label="4" >认同</el-radio>
                <el-radio :label="3" >无所谓</el-radio>
                <el-radio :label="2" >不认同</el-radio>
                <el-radio :label="1" >非常不认同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>

          <el-col :span="24" class="topic_form">12. 请选择对"保持愉悦的心情会提高你对环境的容忍程度"这一命题的认同程度</el-col>
          <el-col :span="24" class="option_form">
            <el-form-item prop="tolerance">
              <el-radio-group v-model="infoForm.tolerance" class = el-radio_label>
                <el-radio :label="5" >非常认同</el-radio>
                <el-radio :label="4" >认同</el-radio>
                <el-radio :label="3" >无所谓</el-radio>
                <el-radio :label="2" >不认同</el-radio>
                <el-radio :label="1" >非常不认同</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>

    <div slot="footer" class="my-button">
      <el-button @click=resetForm>取 消</el-button>
      <el-button type="primary" @click=submitForm()>提 交</el-button>
    </div>
  </div>
</template>
<script>
export default {
  name: 'PersonInfo',
  data: function () {
    return {
      infoForm: {
        name: '',
        gender: '',
        age: '',
        region: '',
        pref: '',
        sens: '',
        eval: '',
        impInTmp: '',
        impInRh: '',
        impInAQ: '',
        impInAV: '',
        impWW: '',
        impOTmp: '',
        impORh: '',
        impMood: '',
        tolerance: ''
      },
      infoRules: {
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur'},
          {min: 2, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
        ],
        gender: [
          {required: true, message: '请输入性别', trigger: 'blur'}
        ],
        age: [
          {required: true, message: '请输入年龄', trigger: 'blur'}
        ],
        region: [
          {required: true, message: '请选择长期居住地', trigger: 'change'}
        ],
        infoF: [
          {required: true, message: '请选择对工作环境的热偏好', trigger: 'blur'}
        ],
        sens: [
          {required: true, message: '请选择对工作环境热变化的敏感程度', trigger: 'blur'}
        ],
        eval: [
          {required: true, message: '请选择对工作环境的热偏好', trigger: 'blur'}
        ],
        impInTmp: [
          {required: true, message: '请选择室内温度对热体验/热舒适性影响程度', trigger: 'blur'}
        ],
        impInRh: [
          {required: true, message: '请选择室内湿度对热体验/热舒适性影响程度', trigger: 'blur'}
        ],
        impInAQ: [
          {required: true, message: '请选择室内湿度对热体验/热舒适性影响程度', trigger: 'blur'}
        ],
        impInAV: [
          {required: true, message: '请选择对工作环境的热偏好', trigger: 'blur'}
        ],
        impWW: [
          {required: true, message: '请选择对工作环境的热偏好', trigger: 'blur'}
        ],
        impOTmp: [
          {required: true, message: '请选择对工作环境的热偏好', trigger: 'blur'}
        ],
        impORh: [
          {required: true, message: '请选择对工作环境的热偏好', trigger: 'blur'}
        ],
        impMood: [
          {required: true, message: '请选择对工作环境的热偏好', trigger: 'blur'}
        ],
        tolerance: [
          {required: true, message: '请选择对工作环境的热偏好', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    submitForm () {
      try {
        this.$refs.infoForm.validate((valid) => {
          if (valid) {
            alert('submit!')
          } else {
            console.log(valid)
            return false
          }
        })
      } catch (e) {
        console.log(e)
      }
    },
    resetForm () {
      this.$refs.infoForm.resetFields()
    }
  }
}
</script>
<style lang="less">
  .container {
    /* 让内部设置了 position 的元素相对于此元素定位 */
    position: relative;
    /* 父级内容最好滚动条，而不是任由内容撑开 */
    max-height: 550px;
    overflow-y: auto;
  }

  .details-bg{
    width: 780px;
    margin: 20px auto;
  }
  .form_header{
    text-align: center;
    margin-bottom: 15px;
    margin-top: 15px;
  }
  .block_form{
    border: 1px solid #eaeefb;
    padding: 30px 30px;
    border-radius: 6px;
  }
  .topic_form{
    text-align: left;
    margin-left: 20px; /* 文字距离上下有100px外边距 距离左右有200外边距 */
    margin-top: 8px;
    margin-bottom: 8px;
  }
  .option_form{
    text-align: left;
    margin-left: 30px; /* 文字距离上下有100px外边距 距离左右有200外边距 */
  }
  .el-form-item {
    margin-bottom: 0px;
  }
  .el-radio_label {
    padding-left: 10px;
    margin-right: 20px;
  }
  .pre_form{
    border: 1px solid #eaeefb;
    padding: 5px 5px 0;
  }
  .my-button {
    /* 相对父级定位，处于父级的右下角，距离右边30px、底部50px的位置 */
    position: fixed;
    right: 500px;
    bottom: 20px;
  }

</style>
