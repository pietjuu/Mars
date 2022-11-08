const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  css: {
    loaderOptions: {
      sass: {
        additionalData: `@use "@/assets/scss/main.scss"; @use "@/assets/scss/colors.scss"; @use "@/assets/scss/constants.scss"; @use "@/assets/scss/fonts.scss";`
      },
      scss: {
        additionalData: `@use "@/assets/scss/main.scss"; @use "@/assets/scss/colors.scss"; @use "@/assets/scss/constants.scss"; @use "@/assets/scss/fonts.scss";`
      }
    }
  },
  pages: {
    index: {
      // entry for the page
      entry: 'src/main.js',
      // the source template
      template: 'src/public/index.html',
      // output as dist/index.html
      filename: 'index.html',
      // when using title option,
      // template title tag needs to be <title><%= htmlWebpackPlugin.options.title %></title>
      title: 'SHIPPERT',
      // chunks to include on this page, by default includes
      // extracted common chunks and vendor chunks.
      chunks: ['chunk-vendors', 'chunk-common', 'index']
    }
  }
})
