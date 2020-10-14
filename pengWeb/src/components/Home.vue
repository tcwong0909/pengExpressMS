<template>
  <div>
    <el-container>
      <el-header class="el-header">
        <div class="header-title">
          鹏城速运Express
        </div>
        <el-dropdown @command="handleCommand">
            <span class="el-dropdown-link">
              {{user.userAccount}}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="myMessage">个人信息</el-dropdown-item>
            <el-dropdown-item command="mySetting">设置</el-dropdown-item>
            <el-dropdown-item command="logout">注销登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu
            @select="handleSelect">
            <div v-for="(item,index) in menuList" :key="index" v-if="!item.hidden">
              <el-submenu :index="String(index)" v-if="item.children && item.children.length > 0">
                <template slot="title">
                  <i :class="item.icon"></i>
                  <span>{{item.name}}</span>
                </template>
                <div v-for="(citem,cindex) in item.children" :key="cindex">
                  <el-menu-item :index="citem.path">{{citem.name}}</el-menu-item>
                </div>
              </el-submenu>
              <el-menu-item v-else :index="item.path">
                <template slot="title">
                  <i :class="item.icon"></i>
                  <span>{{item.name}}
                </span>
                </template>
              </el-menu-item>
            </div>
          </el-menu>
        </el-aside>
        <el-main>
          <div v-show="this.$router.currentRoute.path === '/home'" class="welcome">
            <div>
              欢迎进入鹏城Express物流管理平台
            </div>
            <div>
              鹏城项目会持续迭代，敬请期待！
            </div>
          </div>
          <el-breadcrumb separator="/" v-show="this.$router.currentRoute.path !== '/home'">
            <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>{{this.$router.currentRoute.name}}</el-breadcrumb-item>
          </el-breadcrumb>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: "Home",
    data() {
      return {
        user: JSON.parse(window.sessionStorage.getItem("user")),
        menuList:[]
      }
    },
    mounted() {
      this.getMenuList();

    },
    methods: {
      getMenuList() {
        this.getRequest("/pengms/menu/listMenus").then(res=>{
          if (res.code == "0000") {
            this.menuList = res.data;
          }
        })
      },
      handleCommand(command) {
        if (command == "logout") {
          this.$confirm('注销登录', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            window.sessionStorage.removeItem("user");
            this.$router.replace("/");
            // this.postRequest("/logout").then(res=>{
            //   if (res.data) {
            //   }
            // })
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });
          });
        }
      },
      handleSelect(index) {
        this.$router.push(index);
      }
    },
  }
</script>

<style scoped>
  .el-dropdown-link {
    cursor: pointer;
  }

  .el-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    background-color: rgba(229, 233, 242, 0.23);
  }

  .header-title {
    font-family: 华文行楷;
    font-size: 30px;
  }

  .welcome {
    font-family: 华文行楷;
    font-size: 30px;
    text-align: center;
    margin-top: 100px;
  }
</style>
