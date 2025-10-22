const base = {
    get() {
        return {
            url : "http://localhost:8080/shopping/",
            name: "shopping",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/shopping/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于Spring Boot的网上药房"
        } 
    }
}
export default base
