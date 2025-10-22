const base = {
    get() {
        return {
            url : "http://localhost:8080/springboothc349102/",
            name: "springboothc349102",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboothc349102/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于Spring Boot的网上药房"
        } 
    }
}
export default base
