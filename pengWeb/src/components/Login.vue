<template>
  <div class="login" >
    <el-card class="box-card" >
      <div slot="header" class="clearfix">
        <span>请登录</span>
      </div>
      <div  class="text item" >
        <div style="padding-top: 3vh;">
          <el-tag class="el-tag">用户名</el-tag>
          <el-input v-model="user.account" placeholder="" class="el-input"></el-input>
        </div>
        <div style="padding-top: 3vh;">
          <el-tag  class="el-tag">密码</el-tag>
          <el-input placeholder="关注公众号，获取登陆密码" v-model="user.password"  class="el-input"></el-input>
        </div>
        <div style="padding-top: 3vh;">
          <el-checkbox v-model="remember">记住密码</el-checkbox>
        </div>
        <div style="padding-top: 3vh;">
          <el-button type="primary" @click="login()" id="el-button">登录</el-button>
        </div>

      </div>
    </el-card>
    <div>
      <img src="../assets/TC.jpg">
    </div>
    <div>
      关注公众号，回复   " 鹏城项目 "   获取登陆密码
    </div>
  </div>
</template>

<script>
  import md5 from 'js-md5'
export default {
  name: 'Login',
  data () {
    return {
        remember:'false',
        user:{
          account:'admin',
          password:'admin-TcTalking1234',
        }
    }
  },
  methods:{
    login(){
      // this.user.password = md5(this.user.password);
        this.postRequest("/pengms/login/doLogin",this.user).then(res=>{
            if (res.code ==="0000"){
              this.$router.replace("/home");
              window.sessionStorage.setItem("user",JSON.stringify(res.data))
            }
        })
    },
  },
  created() {
    document.onkeydown = function() {
      var key = window.event.keyCode;
      if (key == 13) {
        document.getElementById("el-button").click();
      }
    };
  }

}
</script>

<style scoped>
  .login{
    display: flex;
    justify-content: center;
    margin-top: 25vh;
  }
  .box-card{
    width: 30vw;
    height: 50vh;
  }
  .el-tag{
    width: 4vw;
  }
  .el-input{
    width: 15vw;
  }
</style>
