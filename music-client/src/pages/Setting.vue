<template>
    <div class="setting">
        <div class="leftCol">
            <div class="settingsMainHeader">设置</div>
            <ul class="setting-aside">
                <li v-for="(item,index) in settingList" 
                    :key="index"
                    :class="{activeColor: activeName == item.name}"
                    @click="changeItem(item)">
                    {{item.name}}
                </li>
            </ul>
        </div>
        <div class="contentCol">
            <component :is="componentSrc"></component>
        </div>
    </div>
</template>
<script>
import Info from '../components/Info';
import AvatorUpload from '../components/AvatorUpload';
export default {
    name: 'setting',
    components: {
        Info,
        AvatorUpload
    },
    data(){
        return {
            settingList: [
                {name: '个人信息',path: 'Info'},
                {name: '修改头像',path: 'AvatorUpload'}
            ],
            activeName: '个人信息',
            componentSrc: 'Info'
        }
    },
    methods: {
        changeItem(item){
            this.activeName = item.name;
            this.componentSrc = item.path;
        }
    }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/setting.scss';
</style>