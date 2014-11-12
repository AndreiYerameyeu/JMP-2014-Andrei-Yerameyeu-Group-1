var bookmarkApp = bookmarkApp || {};

bookmarkApp.routers = bookmarkApp.routers || {};
bookmarkApp.models = bookmarkApp.models || {};
bookmarkApp.collections = bookmarkApp.collections || {};
bookmarkApp.views = bookmarkApp.views || {};

bookmarkApp.routers.AppRouter = Backbone.Router.extend({
    routes: {
     '*other' : 'defaultRoute'   
    },
    
    defaultRoute: function(other) {
        console.log("Invalid path " + other);   
    }
});

bookmarkApp.models.BookmarkModel = Backbone.Model.extend({
    
});

bookmarkApp.models.TagModel = Backbone.Model.extend({

});

bookmarkApp.models.TagCountModel = Backbone.Model.extend({
    
});

bookmarkApp.collections.TagCountListCollection = Backbone.Collection.extend({
    
});

bookmarkApp.collections.TagListCollection = Backbone.Collection.extend({
    model: bookmarkApp.models.TagModel,
    url: 'tag'
});

bookmarkApp.collections.BookmarkListCollection = Backbone.Collection.extend({
    
});

bookmarkApp.views.BookmarkFormView = Backbone.View.extend({
    
    initialize: function() {
        
    },
    
    render: function() {
        
        return this;
    }
});

bookmarkApp.views.BookmarkListContainerView = Backbone.View.extend({
    
    initialize: function() {
        
    },
    
    render: function() {
        
        return this;
    }
});

bookmarkApp.views.BookmarkView = Backbone.View.extend({
    
    initialize: function() {
        
    },
    
    render: function() {
        
        return this;
    }
});

bookmarkApp.views.BookmarkListView = Backbone.View.extend({
    
    initialize: function() {
        
    },
    
    render: function() {
        
        return this;
    }
});

bookmarkApp.views.TagCountListView = Backbone.View.extend({
    
    initialize: function() {
        
    },
    
    render: function() {
        
        return this;
    }
});

bookmarkApp.views.TagCountView = Backbone.View.extend({
    
    initialize: function() {
        
    },
    
    render: function() {
        
        return this;
    }
});



var TagCountView = Backbone.View.extend({
    initialize: function() {
        
    },
    
    render: function() {
        
        return this;
    }
});

Backbone.history.start({
    pushState: true/false,
    root: ''
});

$(document).ready(function() {
   var tagCollection = new bookmarkApp.collections.TagListCollection();
    tagCollection.fetch({
    success: function(collection) {
      console.log(collection);  
    }
    });
});