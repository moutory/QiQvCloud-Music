export const mixin = {
    methods: {
        notify(title,type){
            this.$notify({
                title: title,
                type: type
            })
        },
        getUrl(url){
            return `${this.$store.state.HOST}/${url}`
        },
        /* 根据业务规则转换性别 */
        getCNSex(value){
            if(value == 0){
                return '男';
            }
            if(value == 1){
                return '女';
            }
            if(value == 2){
                return '组合';
            }
            if(value == 3){
                return '不明';
            }
            return value;
        },
        attachBirth(value){
            return String(value).substring(0,10);
        },
         /* 检查上传的图片格式 */
         checkUploadFile(file){
            const isLegal = (file.type === 'image/jpeg' || file.type === 'image/png')
            if(!isLegal){
                this.$message.error('上传的图片只能是jpg或者png格式');
                return false;
            }
            const isOver2M = (file.size /1024/1024) >2
            if(isOver2M){
                this.$message.error('上传的图片不得大于2MB');
                return false;
            }
            return true; 
        },
        /* 歌手头像上传结果 */
        handleAvatorSuccess(res){
            let that = this;
            if(res.code == 200){
                //重新刷新一次页面
                that.getAllSingersData()
                that.$notify({
                    title: '上传成功',
                    type: 'success'
                });
            }else if(res.code ==500){
                that.$notify({
                    title: '上传失败',
                    type: 'error'
                })
            }else{
                that.$notify({
                    title: '上传失败',
                    type: 'error'
                })
            }
        },
        handleDelete(row){
            this.delForm={
                id: row.id
            }
            this.delDialogVisible = true;
        },
        handleSelectItem(val){
            this.multipleSelection=val;
            
        },
        delAll(){
            for(let item of this.multipleSelection){
                this.delForm={
                    id: item.id
                }
                this.delSinger(item);
            }
            this.batchDelDialogVisible=false;
        },
        /* 检查上传的歌曲格式是否正确 */
        checkSongFileType(file){
            if(!file.name){
                this.$message.error('图片上传失败，请重新上传');
                return false;
            }
            var fileSuffix = file.name.substring(file.name.lastIndexOf('.')+1);
            if('mp3' != fileSuffix){
                this.$message.error('上传的音乐文件只能是mp3格式');
                return false;
            }
            return true; 
        },
        /* 更新专辑图片后刷新页面 */
        handleUploadSongPicSuccess(res){
            let that = this;
            if(res.code == 200){
                that.getAllSongsData();
                that.$notify({
                    title: '上传成功',
                    type: 'success'
                });
            }else if(res.code == 500){
                that.$notify({
                    title: '更新专辑图片失败',
                    type: 'success'
                });
            }else{
                that.$notify({
                    title: '更新专辑图片',
                    type: 'success'
                });
            }
        }
        
    }
}