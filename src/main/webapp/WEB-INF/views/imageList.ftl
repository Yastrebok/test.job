<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <title>Images</title>
</head>
<body>
<div>
    <a role="button" class="btn btn-primary" href="/menu">Menu</a>
    <a role="button" class="btn btn-secondary" href="/image/add">Add image</a>
    <a role="button" class="btn btn-secondary" href="/image/all">Refresh</a>

    <nav class="navbar navbar-light bg-faded">
        <form class="form-inline img-t border border-dark ">
            <div class="input-group">
  	  <span class="input-group-addon" id="basic-addon3">
  	  	<select name="myChose" class="custom-select" id="setChoose">
         <option value="1">search by title</option>
        </select>
      </span>
                <input class="form-control mr-sm-2" type="text" id="myInput" onkeyup="Search()" placeholder="Search">
            </div>
        </form>
    </nav>
</div>
<h1 align="center">Image list</h1>
<div class="container img-t">
    <div class="table-responsive">
    <#--<table id="imageTable" class="table table-striped">-->
        <table id="imageTable" class="table table-bordered table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Description</th>
                <th>Category</th>
            <#--<th>Image</th>-->
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>

        <#list list as image>
        <tr>
            <td>${image.id}</td>
            <td>${image.title}</td>
            <td>${image.description}</td>
            <td>${image.category}</td>
        <#--<td><img src=${image.image}/></td>-->
            <td><a role="button" class="btn btn-primary" href="/image/update/${image.id}">Edit</a></td>
            <td><a role="button" class="btn btn-primary" href="/image/delete/${image.id}">Delete</a></td>
        </tr>
        </#list>
            </tbody>
        </table>
    </div>
</div>


</div>

<script>
    function Search() {
        var x = document.getElementById("setChoose").value;
        // Declare variables
        var input, filter, table, tr, td, i, k;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("imageTable");
        tr = table.getElementsByTagName("tr");
        th = table.getElementsByTagName("th");

        // Loop through all table rows, and hide those who don't match the search query

        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[x];
            if (td) {
                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }

    }
</script>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>