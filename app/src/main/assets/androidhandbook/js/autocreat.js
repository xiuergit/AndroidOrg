function autocreate(){
    //创建table表格
    var table=document.createElement("table");
    table.setAttribute("border","1");
    table.setAttribute("background","red");
    //获取行数值
    var line=document.getElementById("line").value;
    var list=document.getElementById("list").value;


   var array=new Array("1","2","3","4");

   //document.write(array[0]);

    for(var i=0;i<=line;i++){

        //创建tr
        var tr=document.createElement("tr");
        for(var j=0;j<=list;j++){
            var td=document.createElement("td");

            td.appendChild(document.createTextNode(array[j]));

            tr.appendChild(td);
                        }
            table.appendChild(tr);
        }


        document.getElementById("d1").appendChild(table);
    }