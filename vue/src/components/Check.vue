<template>
  <div>
    <button class="check-button" v-on:click="onClick">
      <img
        v-bind:src="
          checkedStatus
            ? 'https://media.istockphoto.com/id/1289427302/vector/check-out-button-rounded-sign-on-white-background.jpg?s=612x612&w=0&k=20&c=_8X-Ho3T2EJ3QPVs5Is7ahaU5hh2BRldKLbWxKPFvUo='
            : 'https://media.istockphoto.com/id/1289427422/vector/check-in-button-sticker-banner-rounded-glass-sign.jpg?s=612x612&w=0&k=20&c=5EhSNKqgoMeSrhbr5-n5jMVFiQS2m1NiavhF5wAOLsM='"
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
      },
      sessionStartTime() {
        return this.$store.state.sessionTimerStart;
      },
      sessionEndTime() {
        return this.$store.state.sessionTimerEnd;
      },
      sessionElapsed() {
        return this.$store.state.sessionTimerEnd - this.$store.state.sessionTimerStart;
      }
  },
  methods: {
    toggleCheck() {
      this.$store.commit('TOGGLE_CHECK');
    },
    onClick() {
      if(this.checkedStatus) {
        if(!confirm("Are you sure you're done?")) {
          this.$store.state.isCheckedIn = true;
          return;
        }
        this.$store.commit('STOP_TIMER');
      } else {
        this.$store.commit('START_TIMER');
        this.$router.push({path: '/machines'});
      }
      this.toggleCheck();
    },
    startSession() {
      this.$store.commit('START_TIMER');
    },
    endSession() {
      if(confirm("Are you sure you're done?")) {
        this.$store.commit('STOP_TIMER');
      } else {
        this.$store.state.isCheckedIn = true;
      }
    }
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