<template>
  <div class="login">
    <p class="login-title">
      - <span style="color: #94d82d">A</span>cade<span style="color: #94d82d"
        >M</span
      >eet -
    </p>
    <el-form
      label-position="top"
      ref="ruleFormRef"
      :model="ruleForm"
      status-icon
      :rules="rules"
      size="large"
      class="demo-ruleForm login-box"
      @keydown.enter="submitForm(ruleFormRef)"
    >
      <!-- 이메일 -->
      <el-form-item prop="email" label="EMAIL">
        <el-input
          v-model.trim="ruleForm.email"
          type="email"
          autocomplete="off"
          placeholder="이메일을 입력해 주세요."
          minlength="100"
        />
      </el-form-item>
      <!-- 비밀번호 -->
      <el-form-item prop="password" label="PASSWORD">
        <el-input
          v-model.trim="ruleForm.password"
          type="password"
          autocomplete="off"
          placeholder="비밀번호를 입력해주세요"
        />
      </el-form-item>

      <!-- 로그인 버튼 -->
      <el-button
        type="success"
        @click="submitForm(ruleFormRef)"
        style="margin: 0; width: 100%"
        >LOGIN</el-button
      >
      <!-- </div> -->
    </el-form>
    <!-- 회원가입 -->
    <router-link
      to="/join"
      style="
        margin-top: 8vh;
        text-decoration: none;
        color: gray;
        margin-left: 0px;
      "
      >회원가입</router-link
    >
  </div>
</template>

<script setup>
import { reactive, ref, toRefs } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

const store = useStore();
const ruleFormRef = ref();
const router = useRouter();

const ruleForm = reactive({
  email: "",
  password: "",
});

// 이메일 유효성 검사
const validateEmail = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("이메일을 입력해 주세요."));
  } else {
    if (value.replace(" ", "") !== value) {
      callback(new Error("공백은 입력할 수 없습니다."));
    }
    callback();
  }
};
// 비밀번호 유효성 검사
const validatePassword = (rule, value, callback) => {
  if (value === "") {
    callback(new Error("비밀번호를 입력해주세요"));
  } else {
    if (value.replace(" ", "") !== value) {
      callback(new Error("공백은 입력할 수 없습니다."));
    } else {
      callback();
    }
  }
};

const rules = reactive({
  email: [{ validator: validateEmail, trigger: "blur" }],
  password: [{ validator: validatePassword, trigger: "blur" }],
});

// 로그인
const submitForm = (formEl) => {
  if (!formEl) return;
  formEl.validate(async (valid) => {
    if (valid) {
      const loginData = {
        email: ruleForm.email,
        password: ruleForm.password,
      };
      try {
        await store.dispatch("accountStore/login", loginData);
        // console.log("submit!");
      } catch (error) {
        console.log(error);
      }
    } else {
      console.log("error submit!");
      return false;
    }
  });
};
</script>

<style>
.login {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.login-box {
  min-width: 240px;
  max-width: 400px;
}

.login-title {
  margin: 32px;
  font-size: 50px;
}
</style>
