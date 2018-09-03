package com.users.data;

public class UsersUpdate {
	
	// class variables,global varibles

		String name;
		String job;
		String id;
		String UpdatedAt;
		
		public UsersUpdate()
		{
			
		}

		public UsersUpdate(String name, String job) {
			// global=local vars
			this.name = name;
			this.job = job;
			this.id=id;
			this.UpdatedAt=UpdatedAt;
		
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getJob() {
			return job;
		}

		public void setJob(String job) {
			this.job = job;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUpdatedAt() {
			return UpdatedAt;
		}

		public void setUpdatedAt(String updatedAt) {
			UpdatedAt = updatedAt;
		}


}
