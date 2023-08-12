<template>
  <div class="employee-portal">
    <h2>Employee Portal</h2>
    <div class="search-input">
  <svg class="icon" aria-hidden="true" viewBox="0 0 24 24">
    <path d="M21.53 20.47l-3.66-3.66C19.195 15.24 20 13.214 20 11c0-4.97-4.03-9-9-9s-9 4.03-9 9 4.03 9 9 9c2.215 0 4.24-.804 5.808-2.13l3.66 3.66c.147.146.34.22.53.22s.385-.073.53-.22c.295-.293.295-.767.002-1.06zM3.5 11c0-4.135 3.365-7.5 7.5-7.5s7.5 3.365 7.5 7.5-3.365 7.5-7.5 7.5-7.5-3.365-7.5-7.5z"></path>
  </svg>
  <input
    type="text"
    v-model="searchQuery"
    @input="searchMembers"
    placeholder="Search gym members"
    class="custom-search-input"
  />
</div>
    <div v-if="filteredMembers.length > 0">
      <div class="member" v-for="member in filteredMembers" :key="member.id">
        <p>
        Name:
        <span @click="showProfile(member)" class="member-name">{{ member.name }}</span>
      </p>
      <div class="check-buttons">
        <button @click="checkIn(member)" :class="{ 'checked-in': member.checkedIn }">
          Check In
        </button>
        <span v-if="member.checkedIn">Checked In at: {{ member.checkInTime }}</span>
        <button @click="checkOut(member)" :class="{ 'checked-out': !member.checkedIn }">
          Check Out
        </button>
        <span v-if="!member.checkedIn">Checked Out at: {{ member.checkOutTime }}</span>
      </div>
      </div>
    </div>
    <div v-else>
      <p>No matching gym members found.</p>
    </div>
    <button @click="hideProfile" v-if="selectedProfileMember">
      Hide Profile
    </button>
    <MemberProfileModal
      :selectedMember="selectedProfileMember"
      v-if="showProfileModal"
      @close="hideProfile"
    />
  </div>
</template>

<script>
import MemberProfileModal from "@/components/GymMemberProfile.vue";

export default {
  components: {
    MemberProfileModal,
  },
  data() {
    return {
      gymMembers: [
        {
          id: 1,
          name: "Bruce Wayne",
          checkedIn: false,
          checkInTime: null,
          checkOutTime: null,
        },
        {
          id: 2,
          name: "Jane Smith",
          checkedIn: false,
          checkInTime: null,
          checkOutTime: null,
        },
        {
          id: 3,
          name: "Michael Jackson",
          checkedIn: false,
          checkInTime: null,
          checkOutTime: null,
        },
        // Add more members as needed
      ],
      selectedProfileMember: null,
      searchQuery: "",
      filteredMembers: [],
      showProfileModal: false,
    };
  },
  methods: {
    searchMembers() {
      if (!this.searchQuery) {
        this.filteredMembers = [];
        return;
      }
      const query = this.searchQuery.toLowerCase();
      this.filteredMembers = this.gymMembers.filter((member) =>
        member.name.toLowerCase().includes(query)
      );
    },
    checkIn(member) {
      member.checkedIn = true;
      member.checkInTime = new Date().toLocaleTimeString();
    },
    checkOut(member) {
      member.checkedIn = false;
      member.checkOutTime = new Date().toLocaleTimeString();
    },
    showProfile(member) {
      this.selectedProfileMember = member;
      this.showProfileModal = true;
    },
    hideProfile() {
      this.selectedProfileMember = null;
      this.showProfileModal = false;
    },
    employeeCheck() {
        if(this.$store.state.user.authorities[0].name === "ROLE_USER") {
            this.$router.push({path: '/'});
        }
    }
  },
};
</script>

<style scoped>
.employee-portal {
  
  width: 1;
  height: 254px;
  border-radius: 30px;
  background: #e0e0e0;
  box-shadow: 15px 15px 30px #bebebe, -15px -15px 30px #ffffff;

}

.member {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 20px;
  padding: 10px;
  border: 1px solid #ddd;
  border-radius: 0.5rem;
  background: black;
  background: -webkit-linear-gradient(to right, #3f4c6b, #606c88);
  background: linear-gradient(to right top, #3f4c6b, #606c88);
  padding: 1rem;
  color: #ffffff;
  box-shadow: 0px 87px 78px -39px rgba(0, 0, 0, 0.4);
  margin: auto;
  width: 40em;
}
h2 {
  color: blue;
}

.check-buttons {
  display: flex;
  align-items: center;
}

.check-buttons > div {
  margin-right: 10px;
}

.check-buttons button {
  padding: 5px 5.5px;
  border-radius: 50px;
  border: 0;
  background-color: white;
  box-shadow: rgb(0 0 0 / 5%) 0 0 8px;
  letter-spacing: 1.5px;
  text-transform: uppercase;
  font-size: 15px;
  transition: all 0.5s ease;
}

.check-buttons button.checked-in:hover {
  letter-spacing: 1px;
  background-color: rgb(24, 220, 129);
  color: hsl(0, 0%, 100%);
  box-shadow: rgb(24, 220, 129) 0px 7px 29px 0px;
}

.check-buttons button.checked-in:active {
  letter-spacing: 1px;
  background-color: rgb(24, 220, 129);
  color: hsl(0, 0%, 100%);
  box-shadow: rgb(24, 220, 129) 0px 7px 29px 0px;
  transform: translateY(10px);
  transition: 100ms;
}

.check-buttons button.checked-out:hover {
  letter-spacing: 1px;
  background-color: rgb(240, 67, 67);
  color: hsl(0, 0%, 100%);
  box-shadow: rgb(240, 67, 67) 0px 7px 29px 0px;
}

.check-buttons button.checked-out:active {
  letter-spacing: 1px;
  background-color: rgb(240, 67, 67);
  color: hsl(0, 0%, 100%);
  box-shadow: rgb(240, 67, 67) 0px 7px 29px 0px;
  transform: translateY(10px);
  transition: 100ms;
}

.member-name:hover {
  cursor: pointer;
  color: darkgray;
  text-decoration: underline; /* Optional: Add an underline to make it look more like a link */
}
.search-input {
 width: 95%;
 height: 40px;
 line-height: 28px;
 padding: 0 1rem;
 padding-left: 2.5rem;
 border: 2px solid transparent;
 border-radius: 8px;
 outline: none;
 background-color: #f3f3f4;
 color: #0d0c22;
 transition: .3s ease;
}
.icon {
 position: absolute;
 left: 1.6rem;
 fill: #9e9ea7;
 width: 1rem;
 height: 2.3rem;
}

.search-input:focus, .search-input:hover{
  outline: none;
 border-color: rgba(234,76,137,0.4);
 background-color: #fff;
 box-shadow: 0 0 0 4px rgb(234 76 137 / 10%);
}
.custom-search-input {
  /* Reset default browser styles */
  appearance: none;
  border: none;
  outline: none;
  background: none;
  font-family: inherit;
  font-size: inherit;
  color: inherit;
  padding: 0;
  margin: 0;
  width: 100%;
}
</style>