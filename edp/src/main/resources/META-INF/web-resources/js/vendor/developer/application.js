(function ($) {
//activate components
  $(function () {
      // Custom Selects
      if ($('[data-toggle="select"]').length) {
          $('[data-toggle="select"]').select2();
      }
      // Checkboxes and RadioButtons
      $('[data-toggle="checkbox"]').radiocheck();
      $('[data-toggle="radio"]').radiocheck();
  });

}(jQuery));
