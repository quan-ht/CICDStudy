<script setup>
import HelloWorld from './components/HelloWorld.vue'
import TheWelcome from './components/TheWelcome.vue'

const fetchData = async () => {
  try {
    const response = await fetch('http://192.168.232.129:8100/hello');
    const data = await response.text();
    alert(data);
  } catch (error) {
    alert('请求失败: ' + error.message);
  }
}
</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" height="125" src="./assets/logo.svg" width="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />
      <button @click="fetchData">请求接口</button>
    </div>
  </header>

  <main>
    <TheWelcome />
  </main>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}
</style>
