function CheckInputs() {
      
         if(( document.updateform.text.value == "" ) && (document.updateform.url.value == "")){
            
            document.getElementById("textError").innerHTML = "At least one must not be blank! !";
            return false;
         }
         else
         {
            document.getElementById("textError").innerHTML = "";
         }

          var checkedguitar = document.getElementById('guitar').checked;
          var checkedpiano = document.getElementById('piano').checked;
          var checkeddrums = document.getElementById('drums').checked;
          var checkedflute = document.getElementById('flute').checked;

          if(checkedguitar == false && checkedpiano == false && checkeddrums == false && checkedflute == false)
           {
            document.getElementById("instrumentError").innerHTML ='You need to select an option!';
            return false;
           }
           else
           {
            document.getElementById("instrumentError").innerHTML = "";

           }
        
         document.getElementById("textError").innerHTML = "";
         document.getElementById("instrumentError").innerHTML = "";
         
         return( true );
      }