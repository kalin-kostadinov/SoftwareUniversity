function solve(input) {

   let num = input.toString();

   let sum = 0;
   let isSame = true;

   for (let i = 0; i < num.length; i++) {
        if(num.charAt(0) != num.charAt(i)) {
            isSame = false;
        }  
        sum += Number(num.charAt(i));
   }

   console.log(isSame);
   console.log(sum);

}

solve(2222222);