const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,

  pluginOptions: {
    vuetify: {
      // https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
    }
  }
})

// vue 빌드시 스프링부트에 정적 폴더로 디플로이 작업
module.exports = {
  // npm run build 타겟 디렉토리
  outputDir: '../port/src/main/resources/static',

  // npm run serve 개발 진행시에 포트가 다르기때문에 프록시 설정
  devServer: {
    proxy: 'http://localhost:8080'
  },

  pluginOptions: {
    vuetify: {}
  }
}
