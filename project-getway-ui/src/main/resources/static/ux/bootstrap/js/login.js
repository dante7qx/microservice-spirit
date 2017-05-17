var LoginPage = {
	contextPath: '',
	init: function(contextPath) {
		this.contextPath = contextPath;
		this.initLocation();
	},
	initLocation: function() {
		console.log(this.location+'---->'+self.location+", ---->"+top.location);
		if(self.location && top.location != self.location){
    	    top.location.replace('loginpage');
			return;
    	}
	}
}