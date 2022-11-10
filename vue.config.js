const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  css: {
    loaderOptions: {
      sass: {
        additionalData: `
          @use "@/assets/scss/colors.scss";
          @use "@/assets/scss/constants.scss";
          @use "@/assets/scss/fonts.scss";
        `
      }
    }
  }
})
