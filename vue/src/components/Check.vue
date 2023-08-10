<template>
  <div>
    <button class="check-button" v-on:click="toggleCheck">
      <img
        v-bind:src="
          checkedStatus
            ? 'https://png.pngtree.com/png-vector/20230330/ourmid/pngtree-click-the-done-button-and-tick-icon-vector-png-image_6674611.png'
            : 'https://www.pngkit.com/png/detail/28-282402_check-in-button-2-check-in-button-png.png'"
        v-bind:alt="checkedStatus ? 'Check Out' : 'Check In'"
        class="check-image"
      />
    </button>
  </div>
</template>

<script>
export default {
  name: "check",
  computed: {
      checkedStatus() {
          return this.$store.state.isCheckedIn;
      }
  },
  methods: {
    toggleCheck() {
      this.$store.commit('TOGGLE_CHECK');
    },
  },
};
</script>

<style>
.check-button {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: none;
  background-color: transparent;
  outline: none;
  display: grid;
}

.check-image {
  display: flex;
  flex-grow: 1;
  justify-content: center;
  align-content: center;
}
</style>