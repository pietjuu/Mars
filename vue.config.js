const { defineConfig } = require('@vue/cli-service')
const CONFIG = require("./src/config.json");

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
  },
  publicPath: process.env.NODE_ENV === 'production' ? `/${CONFIG.group}` : '/'
})
