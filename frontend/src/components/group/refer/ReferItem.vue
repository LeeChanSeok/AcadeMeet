<template>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
          <th>No.</th>
          <th>제목</th>
          <th>STT</th>
          <th>지난 회의 영상</th>
          <th>날짜</th>
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody v-if="referListLength > 0">
        <tr v-for="(item, index) in referList" :key="index">
          <td>{{ index + 1 }}</td>
          <td>{{ item.title }}</td>

          <td>
            <a
              :href="`https://i8d108.p.ssafy.io/stt/${item.stt}`"
              v-if="item.video != null"
              >회의록다운로드</a
            >
          </td>

          <td><a :href="item.video" v-if="item.video">영상다운로드</a></td>
          <td>{{ item.date }}</td>
        </tr>
      </tbody>
      <tbody v-else>
        <div style="font-size: 20px; margin-top: 250px">- 정보가 없습니다-</div>
      </tbody>
    </table>
  </div>

  <p hidden>{{ referList }}</p>
</template>

<script setup>
import axios from "axios";
import { requestRefer } from "@/common/api/groupAPI";
import { ref, onMounted } from "vue";
import { useRouter, useRoute } from "vue-router";

const router = useRouter();
const route = useRoute();

// const groupId = ref(route.params.groupId);
const groupId = ref(route.params.groupId);
const referList = ref([]);
var referListLength = 0;

// stt다운로드
const fileDownload = (data) => {
  axios
    .get(`https://i8d108.p.ssafy.io/stt/${data}`, {
      responseType: "blob",
    })
    .then((response) => {
      const url = window.URL.createObjectURL(new Blob([response.data]));
      const link = document.createElement("a");
      link.href = url;
      link.setAttribute("download", "test.txt"); //or any other extension
      document.body.appendChild(link);
      link.click();
    })
    .catch((error) => {
      console.log(error);
      alert("파일 다운로드 실패");
    });
};

onMounted(async () => {
  const res = await requestRefer(groupId.value);

  const datas = res.data;
  const list = datas.map((item) => {
    return {
      title: item.title,
      stt: item.stt,
      video: item.video,
      date: item.date.substr(0, 10),
    };
  });
  referList.value = list;
  referListLength = referList.value.length;
});
</script>

<style>
.detail-btn {
  width: 140px;
  height: 30px;
  font-size: 15px;
}
tr:hover {
  background-color: rgba(97, 178, 153, 0.2);
  font-weight: 700;
  /* color: #fdce7e; */
  color: rgba(97, 178, 153, 1);
}
table {
  width: 100%;
  table-layout: fixed;
}
.tbl-header {
  background-color: rgba(255, 255, 255, 0.3);
}
.tbl-content {
  height: 400px;
  overflow-x: auto;
  margin-top: 0px;
  border: 1px solid rgba(255, 255, 255, 0.3);
}
th {
  padding: 20px 15px;
  text-align: center;
  font-size: 20px;
  font-weight: 700;
  text-transform: uppercase;
  /* background-color: #94d82d; */
  background-color: rgba(97, 178, 153, 1);
}
td {
  padding: 15px;
  text-align: center;
  vertical-align: middle;
  font-weight: 700;
  font-size: 18px;
  /* color: #fff; */
  border-bottom: solid 1px rgba(255, 255, 255, 0.1);
}
/* for custom scrollbar for webkit browser  */

::-webkit-scrollbar {
  width: 6px;
}
::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
}
::-webkit-scrollbar-thumb {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
}
</style>
