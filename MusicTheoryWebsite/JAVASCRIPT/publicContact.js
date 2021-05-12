$(document).ready(function() {
  $('#butsave').on('click', function() {
    var name = $('#name').val();
    var comment = $('#comment').val().trim();
    if(comment!="" ){
      $.ajax({
        url: "../Contact/send.php",
        type: "POST",
        data: {
          name: name,
          comment: comment       
        },
        cache: false,

        success: function(dataResult){
             alert("Comment submitted successfully !"); 
        }
      });
    }
    else{
      alert('Invisible comments will be quite hard to read! Say something!');
    }
  });
});

