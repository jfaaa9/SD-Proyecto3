import { defineConfig } from 'vite';
import vue from '@vitejs/plugin-vue';
import polyfillNode from 'rollup-plugin-polyfill-node';

export default defineConfig({
  plugins: [
    vue(),
    polyfillNode()
  ],
  resolve: {
    alias: {
      '@': 'C:/Users/holas/Documents/Maven/sd-proyecto3/client-vue/src',
    },
  },
  build: {
    rollupOptions: {
      plugins: [polyfillNode()]
    }
  }
});

      
