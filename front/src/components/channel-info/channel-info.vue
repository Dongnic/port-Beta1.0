<template>
  <!-- Layout -->
  <div class="container">
    <div class="icon">
      <!-- #아이콘 넣기 -->
      <HashTagIcon class="iconElement" :size="24" />
    </div>
    <!-- 부모 내용에서 가져온 channelName String 값 보여주기 -->
    <h1 class="title">{{ chatRoomInfo?.title }}</h1>
    <div class="separator" />
    <div class="description">{{ chatRoomInfo?.discribe }}</div>

    <input type="text" name="search" />
  </div>
</template>

<script>
// hashTagIcon class 사용하기 위해 import
import HashTagIcon from 'vue-material-design-icons/Pound'

// 부모 컴포넌트에서 값을 받아온다.
export default {
  props: {
    // channelName 글자형태로
    chatRoomInfo: Object
  },
  // import 한 hashtagicon 사용을 위해 정의
  components: {
    HashTagIcon
  }
}
</script>

<style lang="scss" scoped>
// 위 내용 : 현재컴포넌트에만 적용 스타일 시킴 scpoed 빼면 전체에 영향을 미침
.container {
  grid-area: ci;
  display: flex;
  // 지정영역안에서 중간에 위치시킴
  align-items: center;
  // global.scss 에서 스타일 변수 가져오기
  background-color: var(--primary); // var() 안에내용은 변수선언 되있음
  padding: 0 17px;
  z-index: 2;
  box-shadow: rgba(0, 0, 0, 0.2) 0 1px 0 0;

  input {
    width: 230px;
    height: 24px;
    border-radius: 5px;
    padding: 0 7px;
    color: var(--white);
    background-color: var(--chat-input);
    position: absolute;
    right: 10px;

    &::placeholder {
      color: var(--font);
    }
  }
}

.title {
  color: var(--font);
  font-size: 16px;
  letter-spacing: 2px;
  margin-left: 9px;
  font-weight: bold;
}

.description {
  color: var(--font);
  font-size: 15px;
}

.separator {
  background-color: var(--font);
  height: 24px;
  width: 1px;
  margin: 0 13px;
}

.icon {
  color: var(--font);

  .iconElement {
    margin-right: 5px;
    cursor: pointer;
    transition: 0.2s;

    &:hover {
      color: var(--white);
    }
  }
}

.search {
  display: flex;
  align-items: center;

  input {
    width: 160px;
    height: 24px;
    padding: 0 5px;
    border-radius: 5px;
    color: var(--white);
    background-color: var(--chat-input);
    margin-right: 10px;

    &::placeholder {
      color: var(--white);
      font-size: 16px;
    }
  }
}
</style>
