<template>
    <div class="upload">
        <div class="title">修改头像</div>
        <hr/>
        <div class="section">
            <el-upload drag
                    :show-file-list="false"
                    :action="uploadAvator()"
                    :on-success="handleAvatorChange"
                    :before-upload="checkUploadFile">
                <i class="el-icon-upload"></i>
                <div>
                    将文件拖到此处，或<span style="color:blue">修改头像</span>
                </div>
                <div slot="tip">只能上传jpg/png文件，且不能超过10MB</div>
            </el-upload>
        </div>
    </div>
</template>
<script>
import {queryUserById} from '../api/index';
import {mixin} from '../mixins/index';
import {mapGetters} from 'vuex';
export default {
    name: 'user-avator-upload',
    mixins: [mixin],
    computed: {
        ...mapGetters([
            'userId'
        ])
    },
    methods: {
        checkUploadFile(file){
            var that = this;
            const isImg = file.type == 'image/jpeg';
            const isLt10M = (file.size/1024/1024)<10
            if(!isImg){
                that.notify('头像的格式只允许是JPG格式','warning');
                return false;
            }
            if(!isLt10M){
               that.notify('上传的文件大小不得大于10M','warning'); 
               return false;
            }
            return true;
        },
        /**
         * 修改头像后的回调函数
         */
        handleAvatorChange(res,file){
            if(res.code == 200){
                this.refreshUserAvator();
                this.notify('修改成功','success')
            }else if(res.code == 500 && res.msg){
                this.notify(res.msg,'error');
            }else{
                this.notify('修改失败','error');
            }
            console.log(res);
            console.log(file);
        },
        /**
         * 更新头像
         */
        uploadAvator(){
            return `${this.$store.state.configure.HOST}/consumer/updateConsumerPic?id=${this.userId}`;
        },
        /**
         * 刷新用户头像
         */
        refreshUserAvator(){
            queryUserById(this.userId).then( res => {
                if(res.code == 200 && res.data){
                    this.$store.commit('setAvator',res.data.avator);
                }else if(res.code == 500 && res.msg){
                    this.notify(res.msg,'error');
                }else{
                    this.notify('修改失败','error');
                }
            }).catch( err => {
                console.log(err);
            })
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/upload.scss';
</style>