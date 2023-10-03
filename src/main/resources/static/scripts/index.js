let student_arr = [];
let auto_arr = [];
let student_id;

function showStudents() {
    $("#table_students tbody").html("");
    $.ajax({
        type: "GET",
        url: `student`,
        success: [function (result) {
            let student = result.data;
            student_arr = student;
            for (let i = 0; i < student.length; i++) {
                let markup = "<tr>" +
                    "<td>" + student[i].fio + "</td>" +
                    "<td>" + student[i].age + "</td>" +
                    "<td>" + student[i].num + "</td>" +
                    "<td>" + student[i].salary + "</td>" +
                    `<td style="text-align: center"><a href="#" id="change_${student[i].id}"><i class="fa fa-edit" style="font-size:20px"></i></a></td>>` +
                    `<td style="text-align: center"><a href="#" id="delete_${student[i].id}"><i class="fa fa-trash" style="font-size:20px"></i></a></td>` +
                    `<td style="text-align: center"><a href="#" id="information_${student[i].id}"><i class="fa fa-info" style="font-size:20px"></i></a></td>` + "</tr>";
                $("#table_students tbody").append(markup);
            }
        }],
        error: [function (e) {
            alert("error23123");
            alert(JSON.stringify(e));
        }]
    });
}

$(document).ready(function () {
    $('#add_student_button').click(function () {
        let fio = $('#add_student_fio').val();
        let age = $('#add_student_age').val();
        let num = $('#add_student_num').val();
        let salary = $('#add_student_salary').val();
        $.ajax({
            type: "POST",
            url: `student`,
            data: JSON.stringify({
                "fio": fio,
                "age": age,
                "num": num,
                "salary": salary
            }),
            contentType: 'application/json',
            success: [function (result) {
                $('#add_student_fio').val('');
                $('#add_student_age').val('');
                $('#add_student_num').val('');
                $('#add_student_salary').val('');
                showStudents();
            }],
            error: [function () {
                alert("error121");
            }]
        });
    });
    $('#table_students tbody').on("click", "a", function () {
        let arr = $(this).attr('id').split('_');
        let id = parseInt(arr[1]);
        let value = arr[0];
        if (value === "change") {
            let student = student_arr.find(o => o.id === id);
            $('#modalChangeStudent').modal('show');
            $('#change_student_id').val(student.id);
            $('#change_student_fio').val(student.fio);
            $('#change_student_age').val(student.age);
            $('#change_student_num').val(student.num);
            $('#change_student_salary').val(student.salary);
        } else if (value === "information") {
            let student = student_arr.find(o => o.id === id);
            student_id = student.id;
            showAutos(student_id);
        } else {
            deleteStudent(id);
        }
    });

    $('#change_student_button').click(function () {
        let id = $('#change_student_id').val();
        let fio = $('#change_student_fio').val();
        let age = $('#change_student_age').val();
        let num = $('#change_student_num').val();
        let salary = $('#change_student_salary').val();
        $.ajax({
            type: "PUT",
            url: 'student',
            data: JSON.stringify({
                "id": id,
                "fio": fio,
                "age": age,
                "num": num,
                "salary": salary
            }),
            contentType: 'application/json',
            success: [function (result) {
                $('#change_student_id').val('');
                $('#change_student_fio').val('');
                $('#change_student_age').val('');
                $('#change_student_num').val('');
                $('#change_student_salary').val('');
                showStudents();
            }],
            error: [function () {
                alert("error878");
            }]
        });
    });
});

function deleteStudent(id) {
    $.ajax({
        type: "DELETE",
        url: `student/${id}`,
        success: [function (result) {
            showStudents();
            showAutos(student_id);
        }],
        error: [function (e) {
            alert(JSON.stringify(e));
        }]
    });
}

function showAutos(id) {
    $("#table_autos tbody").html("");
    $.ajax({
        type: "GET",
        url: `auto/search?id=${id}`,
        success: [function (result) {
            let auto = result.data;
            auto_arr = auto;
            for (let i = 0; i < auto.length; i++) {
                let markup = "<tr>" +
                    "<td>" + auto[i].brand + "</td>" +
                    "<td>" + auto[i].power + "</td>" +
                    "<td>" + auto[i].year + "</td>" +
                    `<td style="text-align: center"><a href="#" id="changeAuto_${auto[i].id}"><i class="fa fa-edit" style="font-size:20px"></i></a></td>` +
                    `<td style="text-align: center"><a href="#" id="deleteAuto_${auto[i].id}"><i class="fa fa-trash" style="font-size:20px"></i></a></td>` + "</tr>";
                $("#table_autos tbody").append(markup);
            }
        }],
        error: [function (e) {
            alert("error999");
            alert(JSON.stringify(e));
        }]
    });
}

$(document).ready(function () {
    $('#add_auto_button').click(function () {
        let brand = $('#add_auto_brand').val();
        let power = $('#add_auto_power').val();
        let year = $('#add_auto_year').val();
        $.ajax({
            type: "POST",
            url: 'auto/' + student_id,
            data: JSON.stringify({
                "brand": brand,
                "power": power,
                "year": year
            }),
            contentType: 'application/json',
            success: [function (result) {
                $('#add_auto_brand').val('');
                $('#add_auto_power').val('');
                $('#add_auto_year').val('');
                showAutos(student_id);
            }],
            error: [function () {
                alert("error444");
            }]
        });
    });
    $('#table_autos tbody').on("click", "a", function () {
        let arr = $(this).attr('id').split('_');
        let id = parseInt(arr[1]);
        let value = arr[0];
        if (value === "changeAuto") {
            let auto = auto_arr.find(o => o.id === id);
            $('#modalChangeAuto').modal('show');
            $('#change_auto_id').val(auto.id);
            $('#change_auto_brand').val(auto.brand);
            $('#change_auto_power').val(auto.power);
            $('#change_auto_year').val(auto.year);
        } else {
            deleteAuto(id);
        }
    });

    $("tbody").on('dblclick', 'tr', function (e) {
        let arr = $(this).attr('id').split('_');
        let id = parseInt(arr[1]);
        let value = arr[0];
        alert(id);
    });


    $('#change_auto_button').click(function () {
        let id = $('#change_auto_id').val();
        let brand = $('#change_auto_brand').val();
        let power = $('#change_auto_power').val();
        let year = $('#change_auto_year').val();
        $.ajax({
            type: "PUT",
            url: 'auto',
            data: JSON.stringify({
                "id": id,
                "brand": brand,
                "power": power,
                "year": year
            }),
            contentType: 'application/json', //////////////////
            success: [function (result) {
                $('#change_card_id').val('');
                $('#add_auto_brand').val('');
                $('#add_auto_power').val('');
                $('#add_auto_year').val('');
                showAutos(student_id);
            }],
            error: [function () {
                alert("error234");
            }]
        });
    });
});

function deleteAuto(id) {
    $.ajax({
        type: "DELETE",
        url: `auto/${id}`,
        success: [function (result) {
            showAutos(student_id);
        }],
        error: [function (e) {
            alert(JSON.stringify(e));
        }]
    });
}


