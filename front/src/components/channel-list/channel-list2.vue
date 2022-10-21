<template>
  <div class="list-container .user-role">
    <div class="category">
<<<<<<< HEAD
      <div class="title-users"><h2>최근 대화</h2></div>
    </div>
    <UserRow
      v-for="(user,index) in dmUserList"
      :key="user.id"
      :no="index"
      :userid="user.id"
      :nickName="user.username"
      :profileimage="user.profileimage"
      :isOnline=3
      @changeRoom="changeRoom"
=======
      <div>최근 DM</div>
    </div>
    <UserRow
      v-for="user in DMUserList"
      :key="user.id"
      :nickName="user.username"
      :profileimage="user.profileimage"
      :isOnline="true"
      @click="toDM(user.id)"
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    />
  </div>
</template>

<script>
import UserRow from '@/components/user-list/user-row'
<<<<<<< HEAD
import $axios from 'axios'

export default {
  props: {
    dmUserList: Object,
    userInfo: Object
=======
import { computed } from 'vue'
import { useStore } from 'vuex'
export default {
  setup () {
    const store = useStore()
    const dmUserList = computed(() => store.getters['module1/getDmUserList'])
    return { dmUserList }
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
  },
  components: {
    UserRow
  },
  data () {
    return {
      channelCount: 3,
      activeChannel: 1
    }
  },
  methods: {
    addChannel () {
      this.channelCount++
    },
    removeChannel () {
      if (this.channelCount > 1) this.channelCount--
      if (this.channelCount < this.activeChannel) this.activeChannel = this.channelCount
    },
    changeChannel (no) {
      this.activeChannel = no
<<<<<<< HEAD
    },
    changeRoom (no) {
      console.log('채널리스트2')
      this.$emit('changeRoom', no)
    },
    toDM (otherid) {
      // console.log('toDM')
      // console.log('otherid : ', otherid)
      // console.log('userid : ', this.userInfo.id)
      if (otherid === this.userInfo.id) {
        alert('자신에게는 DM을 할 수 없습니다.')
      }
      const data = {
        userid: this.userInfo.id,
        otherid: otherid
      }
      $axios
        .post('/api/chat/DM', data)
        .then((response) => {
          alert('DM 요청 c-list 성공')
          this.$emit('changeRoom', 1)
          this.$store.dispatch('module1/setDmInfo', response.data)
        })
        .catch((error) => {
          console.log(error)
        })
=======
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
    }
  }
}
</script>

<style lang="scss" scoped>
.list-container {
  grid-area: cl;
  display: flex;
  flex-direction: column;

<<<<<<< HEAD
  max-height: calc(100vh - 46px);
  padding: 3px 6px 10px 16px;
  // padding: 24px 10px 0 16px;
=======
  padding: 24px 10px 0 16px;
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
  background-color: var(--secondary);
  overflow-y: scroll;

  &::-webkit-scrollbar {
    width: 8px;
  }

  &::-webkit-scrollbar-thumb {
    background-color: var(--tertiary);
    border-radius: 4px;
  }

  &::-webkit-scrollbar-track {
    background-color: var(--secondary);
  }
}

.category {
  display: flex;
  align-items: center;
  justify-content: space-between;

  margin-bottom: 6px;
  color: var(--grey);
  font-size: 12px;
  font-weight: 500;
  text-transform: uppercase;

  .category-icon {
    color: var(--symbol);
    cursor: pointer;
    &:hover {
      color: var(--white);
      transition: all 0.2s;
    }
  }
}
.user-role {
  display: flex;
  margin-top: 5px;
  padding: 5px;
  display: flex;
  align-items: center;
  cursor: pointer;
  border-radius: 5px;
  background: transparent;
  transition: all ease-out 0.2s;

  &:hover {
    background-color: rgba(255, 255, 255, 0.1);
  }

  strong {
    margin-left: 13px;
    font-weight: 500;
    color: var(--white);
    opacity: 0.7;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
  }
  .avatar {
    width: 32px;
    height: 32px;
    flex-shrink: 0;
    background-color: var(--primary);
    border-radius: 50px;
    background-size: cover;
  }

}
<<<<<<< HEAD
.title-users {
  margin-top: 20px;
  color: var(--grey);
  font-weight: 500;
  text-transform: uppercase;
}
=======
>>>>>>> 7fd9fe79b9493c4f812ce0db4d5d81dcb4d70e8b
</style>
