$(function() {
	
	$(".wallet-user tr td:last-child").click(function(){
	    var $this = $(this);
		var denom = $this.parent().find('td:first-child').html();
	    
	    $.post(window.location.pathname + "spendCoin",
	    {
	    	denom: denom
	    },
	    function(data, status){
	    	
	    	console.log(data);
	    	
	        data = $.parseJSON(data);
	        
	        if (data.error) {
	        	alert("Error: " + data.error);
	        } else {
		        var rowIndex = $this.parent().index() + 1; // +1 because of the header column
		        $this.parent().find('td:nth-child(2)').html(data.userCoins);
		        $(".wallet-vm tr:nth-child("+rowIndex+") td:nth-child(2)").html(data.vmCoins); 
	        }
	    });
	});
});