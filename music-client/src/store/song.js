const song = {
    state: {
        listOfSongs: [], //当前的播放列表
        songId : '', // 歌曲id
        songUrl: '', // 歌曲播放地址
        songPic: '', // 专辑图片地址
        songLyric: '', // 歌词
        songName: '',  // 歌名
        singerName: '', // 歌手名
        listIndex: null,    // 当前歌曲在歌单中的位置
        duration: 0,  // 视频时长
        changeTime: 0, // 指定音乐播放时刻
        isPlay: false, // 当前是否播放
        currentTime: 0, // 当前音乐的播放位置
        autoNext: true, // 是否继续播放下一首歌曲
        playButtonUrl: '#icon-bofang', // 播放条的样式，点击切换播放/暂停状态
        volume: 50,     // 音乐音量 
        temList: {},    // 当个歌单或者歌曲的信息，后续可以考虑迁移出去

    },
    // getter
    getters: {
        listOfSongs: state =>{
            let listOfSongs = state.listOfSongs;
            if(!listOfSongs.length){
                return JSON.parse(window.sessionStorage.getItem('listOfSongs')||null);
            }else{
                return listOfSongs;
            }
        },
        songId: state =>{
            let songId = state.songId;
            if(!songId){
                return JSON.parse(window.sessionStorage.getItem('songId')||null);
            }else{
                return songId;
            }
        },
        songUrl: state =>{
            let songUrl = state.songUrl;
            if(!songUrl){
                return JSON.parse(window.sessionStorage.getItem('songUrl')||null);
            }else{
                return songUrl;
            }
        },
        songPic: state =>{
            let songPic = state.songPic;
            if(!songPic){
                return JSON.parse(window.sessionStorage.getItem('songPic')||null);
            }else{
                return songPic;
            }
        },
        songLyric: state =>{
            let songLyric = state.songLyric;
            if(!songLyric){
                return JSON.parse(window.sessionStorage.getItem('songLyric')||null);
            }else{
                return songLyric;
            }
        },
        songName: state =>{
            let songName = state.songName;
            if(!songName){
                return JSON.parse(window.sessionStorage.getItem('songName')||null);
            }else{
                return songName;
            }
        },
        listIndex: state =>{
            let listIndex = state.listIndex;
            if(!listIndex){
                return JSON.parse(window.sessionStorage.getItem('listIndex')||null);
            }else{
                return listIndex;
            }
        },
        singerName: state =>{
            let singerName = state.singerName;
            if(!singerName){
                return JSON.parse(window.sessionStorage.getItem('singerName')||null);
            }else{
                return singerName;
            }
        },
        duration: state => {
            let duration = state.duration;
            if(!duration){
                return JSON.parse(window.sessionStorage.getItem('duration')||null);
            }else{
                return duration;
            }
        },
        isPlay: state => {
            let isPlay = state.isPlay;
            if(!isPlay){
                return JSON.parse(window.sessionStorage.getItem('isPlay')||null);
            }else{
                return isPlay;
            }
        },
        currentTime: state => {
            let currentTime = state.currentTime;
            if(!currentTime){
                return JSON.parse(window.sessionStorage.getItem('currentTime')||null);
            }else{
                return currentTime;
            }
        },
        autoNext: state => {
            let autoNext = state.autoNext;
            if(!autoNext){
                return JSON.parse(window.sessionStorage.getItem('autoNext')||null);
            }else{
                return autoNext;
            }
        },
        playButtonUrl: state => {
            let playButtonUrl = state.playButtonUrl;
            if(!playButtonUrl){
                return JSON.parse(window.sessionStorage.getItem('playButtonUrl')||null);
            }else{
                return playButtonUrl;
            }
        },
        changeTime: state => {
            let changeTime = state.changeTime;
            if(!changeTime){
                return JSON.parse(window.sessionStorage.getItem('changeTime')||null);
            }else{
                return changeTime;
            }
        },
        volume: state => {
            let volume = state.volume;
            if(!volume){
                return JSON.parse(window.sessionStorage.getItem('volume')||null);
            }else{
                return volume;
            }
        },
        temList: state => {
            let temList = state.temList;
            // 这里不直接用temList是因为刷新页面后，vuex中的数据以及被重新初始化，但是temList虽然被清空，但还是有对象，所以这里直接采用对象+属性判断
            // 如果属性没有值，强制从缓存中读取数据
            if(!temList.id){
                return JSON.parse(window.sessionStorage.getItem('temList')||null);
            }else{
                return temList;
            }
        },
    },
    // setter
    mutations: {
        setListOfSongs: (state,listOfSongs) =>{
            state.listOfSongs = listOfSongs;
            window.sessionStorage.setItem('listOfSongs',JSON.stringify(listOfSongs));
        },
        setSongId: (state,songId) => {
            state.songId = songId;
            window.sessionStorage.setItem('songId',JSON.stringify(songId));
        },
        setSongUrl: (state,songUrl) => {
            state.songUrl = songUrl;
            window.sessionStorage.setItem('songUrl',JSON.stringify(songUrl));
        },
        setSongPic: (state,songPic) => {
            state.songPic = songPic;
            window.sessionStorage.setItem('songPic',JSON.stringify(songPic));
        },
        setSongLyric: (state,songLyric) => {
            state.songLyric = songLyric;
            window.sessionStorage.setItem('songLyric',JSON.stringify(songLyric));
        },
        setSongName: (state,songName) => {
            state.songName = songName;
            window.sessionStorage.setItem('songName',JSON.stringify(songName));
        },
        setListIndex: (state,listIndex) => {
            state.listIndex = listIndex;
            window.sessionStorage.setItem('listIndex',JSON.stringify(listIndex));
        },
        setSingerName: (state,singerName) => {
            state.singerName = singerName;
            window.sessionStorage.setItem('singerName',JSON.stringify(singerName));
        },
        setDuration: (state,duration) => {
            state.duration = duration;
            window.sessionStorage.setItem('duration',JSON.stringify(duration));
        },
        setIsPlay: (state,isPlay) => {
            state.isPlay = isPlay;
            window.sessionStorage.setItem('isPlay',JSON.stringify(isPlay));
        },
        setCurrentTime: (state,currentTime) => {
            state.currentTime = currentTime;
            window.sessionStorage.setItem('currentTime',JSON.stringify(currentTime));
        },
        setAutoNext: (state,autoNext) => {
            state.autoNext = autoNext;
            window.sessionStorage.setItem('autoNext',JSON.stringify(autoNext));
        },
        setPlayButtonUrl: (state,playButtonUrl) => {
            state.playButtonUrl = playButtonUrl;
            window.sessionStorage.setItem('playButtonUrl',JSON.stringify(playButtonUrl));
        },
        setChangeTime: (state,changeTime) => {
            state.changeTime = changeTime;
            window.sessionStorage.setItem('changeTime',JSON.stringify(changeTime));
        },
        setVolume: (state,volume) => {
            state.volume = volume;
            window.sessionStorage.setItem('volume',JSON.stringify(volume));
        },
        setTemList: (state,temList) => {
            state.temList = temList;
            window.sessionStorage.setItem('temList',JSON.stringify(temList));
        },
       
    }
}
export default song;