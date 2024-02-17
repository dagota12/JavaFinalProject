/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
   $(document).ready(function(){
       var shoppingCart = (function () {

    cart = [];

    function Item(id,name, price, count) {
      this.id = id;
      this.name = name;
      this.price = price;
      this.count = count;
    }

    // Save cart
    function saveCart() {
      localStorage.setItem('shoppingCart', JSON.stringify(cart));
    }

    // Load cart
    function loadCart() {
      cart = JSON.parse(localStorage.getItem('shoppingCart'));
    }
    if (localStorage.getItem("shoppingCart") != null) {
      loadCart();
    }


    var obj = {};

    // Add to cart
    obj.addItemToCart = function (id,name, price, count) {
      for (var item in cart) {
        if (cart[item].id === id) {
          cart[item].count++;
          saveCart();
          return;
        }
      }
      var item = new Item(id,name, price, count);
      cart.push(item);
      saveCart();
    };
    // Set count from item
    obj.setCountForItem = function (name, count) {
      for (var i in cart) {
        if (cart[i].name === name) {
          cart[i].count = count;
          break;
        }
      }
    };
    // Remove item from cart
    obj.removeItemFromCart = function (name) {
      for (var item in cart) {
        if (cart[item].name === name) {
          cart[item].count--;
          if (cart[item].count === 0) {
            cart.splice(item, 1);
          }
          break;
        }
      }
      saveCart();
    };

    // Remove all items from cart
    obj.removeItemFromCartAll = function (name) {
      for (var item in cart) {
        if (cart[item].name === name) {
          cart.splice(item, 1);
          break;
        }
      }
      saveCart();
    };

    // Clear cart
    obj.clearCart = function () {
      cart = [];
      saveCart();
    };

    // Count cart 
    obj.totalCount = function () {
      var totalCount = 0;
      for (var item in cart) {
        totalCount += cart[item].count;
      }
      return totalCount;
    };

    // Total cart
    obj.totalCart = function () {
      var totalCart = 0;
      for (var item in cart) {
        totalCart += cart[item].price * cart[item].count;
      }
      return Number(totalCart.toFixed(2));
    };

    // List cart
    obj.listCart = function () {
      var cartCopy = [];
      for (const i in cart) {
        item = cart[i];
        itemCopy = {};
        for (const p in item) {
          itemCopy[p] = item[p];
        }
        itemCopy.total = Number(item.price * item.count).toFixed(2);
        cartCopy.push(itemCopy);
      }
      return cartCopy;
    };
    return obj;
  })();


  // Add item
  $('.default-btn').click(function (event) {
    // alert('working');
    event.preventDefault();
    var name = $(this).data('name');
    var id = $(this).data('id');
    var price = Number($(this).data('price'));
    shoppingCart.addItemToCart(id,name, price, 1);
    displayCart();
  });

  // Clear items
  $('.clear-cart').click(function () {
    shoppingCart.clearCart();
    displayCart();
  });


  function displayCart() {
    var cartArray = shoppingCart.listCart();
    var output = "";
    for (var i in cartArray) {
    output += `
      <tr>
        <td>${cartArray[i].name}</td>
        <td>(${cartArray[i].price})</td>
        <td>
          <div class='input-group'>
            <input type='number' class='item-count form-control' data-name='${cartArray[i].name}' value='${cartArray[i].count}'>
          </div>
        </td>
        <td>
          <button class='delete-item btn btn-danger text-danger' data-name='${cartArray[i].name}'>X</button>
        </td>
        = 
        <td>${cartArray[i].total}</td>
      </tr>`;
    }
    $('.show-cart').html(output);
    $('.total-cart').html(shoppingCart.totalCart());
    $('.total-count').html(shoppingCart.totalCount());
  }

  // Delete item button

  $('.show-cart').on("click", ".delete-item", function (event) {
    var name = $(this).data('name');
    shoppingCart.removeItemFromCartAll(name);
    displayCart();
  });

  // Item count input
  $('.show-cart').on("change", ".item-count", function (event) {
    var name = $(this).data('name');
    var count = Number($(this).val());
    shoppingCart.setCountForItem(name, count);
    displayCart();
  });
  displayCart();

//////// ui script start /////////
// Tabs Single Page
$('.tab ul.tabs').addClass('active').find('> li:eq(0)').addClass('current');
$('.tab ul.tabs li a').on('click', function (g) {
    var tab = $(this).closest('.tab'), 
    index = $(this).closest('li').index();
    tab.find('ul.tabs > li').removeClass('current');
    $(this).closest('li').addClass('current');
    tab.find('.tab_content').find('div.tabs_item').not('div.tabs_item:eq(' + index + ')').slideUp();
    tab.find('.tab_content').find('div.tabs_item:eq(' + index + ')').slideDown();
    g.preventDefault();
});
// search function
$('#search_field').on('keyup', function() {
  var value = $(this).val();
  var patt = new RegExp(value, "i");

  $('.tab_content').find('.col-lg-3').each(function() {
    var $table = $(this);
    
    if (!($table.find('.featured-item').text().search(patt) >= 0)) {
      $table.not('.featured-item').hide();
    }
    if (($table.find('.col-lg-3').text().search(patt) >= 0)) {
      $(this).show();
      document.getElementById('not_found').style.display = 'none';
    } else {
      document.getElementById("not_found").innerHTML = " Product not found..";
      document.getElementById('not_found').style.display = 'block';
    }

  });
  
});
$("#buy").click(function () {
//    console.log(cart); // Assuming `cart` is the array of cart elements
     const sentData = {
         id:userId, // user id is initialized the dashboard file
         cart:cart
     };
     
     //Send the AJAX POST request
    $.ajax({
        url: "buy",
        type: "POST",
        data: JSON.stringify(cart),
        contentType: "application/json",
        success: function (response) {
            // Handle the success response from the server
            showAlert('Success', 'Purchase successful', 'alert-success');
            console.log("Purchase successful");
            alert("Purchase successful");
            // Add any additional logic or UI updates here
        }, 
        error: function (xhr, status, error) {
            // Handle the error response from the server
            showAlert('Error', 'Purchase failed: ' + xhr.responseText, 'alert-danger');
            console.log("Purchase failed");
            console.log(xhr.responseText);
            // Add any error handling or UI updates here
        }
    });
});
    
    function showAlert(type, message, alertClass) {
        // Set the alert type, message, and class
        $('#alertMessage').text(message);
        $('#alertContainer').removeClass("d-none");
        $('#alertContainer').addClass("d-block");
        $('#alertContainer').removeClass('alert-success alert-danger').addClass(alertClass);

        // Show the alert and hide after a few seconds
        $('#alertContainer').slideDown();
        setTimeout(function () {
            $('#alertContainer').removeClass("d-block");
            $('#alertContainer').addClass("d-none");
            $('#alertContainer').slideUp();
        }, 5000);
    }
   });
