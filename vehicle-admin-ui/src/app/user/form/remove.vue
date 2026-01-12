<template>
  <div>
    <Modal width="300" v-model="removeModal" @on-cancel="handleCancel">
      <p slot="header" style="color:#f60;">
        <Icon type="information-circled"></Icon>
        <span>{{title}}</span>
      </p>
      <div style="text-align:center">
        <p>确认删除该记录？</p>
      </div>
      <div slot="footer">
        <Button type="error" size="small" long :loading="removeLoading" @click="handleOk">
          <span v-if="!removeLoading">{{okText}}</span>
          <span v-else>{{okText}}</span>
        </Button>
      </div>
    </Modal>
  </div>
</template>

<script>
  export default {
    name: 'removeModal',
    props: {
      id: { type: String, default: '' },
      modal: { type: Boolean, default: false },
      title: { type: String, default: '用户删除' },
      okText: { type: String, default: '确认' }
    },
    created () {},
    mounted () {},
    data () {
      return {
        removeModal: false,
        removeLoading: false,
        removeForm: {
          id: ''
        }
      }
    },
    watch: {
      modal: function (val) {
        if (val && this.id !== '') {
          this.removeForm.id = this.id
          this.removeModal = val
        }
      },
      removeModal: function (val) {
        if (!val) {
          this.$emit('on-modal-change', '', val)
        }
      }
    },
    methods: {
      handleOk () {
        this.removeLoading = true
        this.$store.dispatch('removeUser', {
          data: {
            id: this.removeForm.id
          }
        }).then(() => {
          this.handleCancel()
          this.$Message.success(this.title + '成功！')
          this.$emit('on-refresh-page')
        }, (res) => {
          this.handleCancel()
          this.$Message.error(res.data.message)
        })
      },
      handleCancel () {
        this.removeForm.id = ''
        this.removeModal = false
        this.removeLoading = false
      }
    }
  }
</script>
