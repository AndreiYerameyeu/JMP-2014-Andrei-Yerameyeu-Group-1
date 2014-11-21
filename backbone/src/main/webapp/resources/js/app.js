$.fn.serializeObject = function() {
  var o = {};
  var a = this.serializeArray();
  $.each(a, function() {
      if (o[this.name] !== undefined) {
          if (!o[this.name].push) {
              o[this.name] = [o[this.name]];
          }
          o[this.name].push(this.value || '');
      } else {
          o[this.name] = this.value || '';
      }
  });
  return o;
};

var bookmarkApp = bookmarkApp || {};

bookmarkApp.routers = bookmarkApp.routers || {};
bookmarkApp.models = bookmarkApp.models || {};
bookmarkApp.collections = bookmarkApp.collections || {};
bookmarkApp.views = bookmarkApp.views || {};

bookmarkApp.routers.AppRouter = Backbone.Router.extend({
    routes: {
     '' : 'home',
     'save' : 'saveBookmark',
     'edit/:id': 'editBookmark',
     'delete/:id': 'deleteBookmark',
     '*other' : 'defaultRoute'   
    },
    
    home: function() {
    	var tagCountListView = new bookmarkApp.views.TagCountListView();
        var bookmarkListView = new bookmarkApp.views.BookmarkListView();
        var bookmarkFormView = new bookmarkApp.views.BookmarkFormView();
        bookmarkFormView.render({model : new bookmarkApp.models.BookmarkModel()});
    },  
    saveBookmark: function(id) {
    	
    },
    editBookmark: function(id) {
    	var bookmarkFormView = bookmarkApp.views.BookmarkFormView();
    	bookmarkFormView.render({id: id});
    },
    deleteBookmark: function(id) {
    	
    },
    defaultRoute: function(other) {
        console.log("Invalid path " + other);   
    }
});

bookmarkApp.models.BookmarkModel = Backbone.Model.extend({
	defaults: {
	    url: '',
	    title: '',
	    tags: ''
	  }, 
	urlRoot: 'bookmark',
	validate: function(attrs) {
		if (attrs.url === undefined || attrs.url.length < 1) {
			return 'No url';
		}
		if (attrs.title === undefined || attrs.title.length < 1) {
			return 'No title';
		}
		if (attrs.tags === undefined || attrs.tags.length < 1) {
			return 'No tags';
		}
	} 
});

bookmarkApp.models.TagModel = Backbone.Model.extend({
});

bookmarkApp.models.TagCountModel = Backbone.Model.extend({ 
});

bookmarkApp.collections.TagCountListCollection = Backbone.Collection.extend({
    model: bookmarkApp.models.TagCountModel,
    url: 'tag/tagcount'
});

bookmarkApp.collections.TagListCollection = Backbone.Collection.extend({
    model: bookmarkApp.models.TagModel,
    url: 'tag?bookmarkid='
});

bookmarkApp.collections.BookmarkListCollection = Backbone.Collection.extend({
    model: bookmarkApp.models.BookmarkModel,
    url: 'bookmark'
});

bookmarkApp.views.BookmarkFormView = Backbone.View.extend({
	model: bookmarkApp.models.BookmarkModel,
    el: $('#formAppender'),
    initialize: function() {
    	this.template = _.template($('#bookmark-form-template').html());
    },
    events: {
    	'submit #form': 'saveBookmark',
    	'click #btnSave': 'saveBookmark',
    	'click #btnClear': 'clearForm'
    },
    render: function (options) {
    	if (options !== undefined) {
    		this.model = options.model;
    	} else {
    		this.model = new bookmarkApp.models.BookmarkModel();
    	}
    	var that = this;
    	this.$el.html(this.template(this.model.toJSON()));
    	/*if(options.id) {
    		that.user = new User({id: options.id});
    		that.user.fetch({
    			success: function (user) {
    				var template = _.template($('#edit-user-template').html(), {user: user});
    				that.$el.html(template);
    			}
    		});
    	} else {
    		var template = _.template($('#edit-user-template').html(), {user: null});
    		that.$el.html(template);
    	}*/
    },
    renderAlert: function() {
    	
    },
    saveBookmark: function(event) {
    	var bookmarkValues = $('#bookmark-form').serializeObject();
    	var bookmarkModel = new bookmarkApp.models.BookmarkModel(bookmarkValues);
    	bookmarkModel.save({
    		success: function (bookmark) {
    			router.navigate('', {trigger:true});
    		},
    		error: function (error) {
    			console.log(error);
    		}
    	});
    	return false;
    },
    clearForm: function() {
    	
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
    model: bookmarkApp.models.BookmarkModel,
    tagName: 'li',
    initialize: function() {
    	this.template = _.template($('#bookmark-template').html());
    },
    render: function() {
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    }
});

bookmarkApp.views.BookmarkListView = Backbone.View.extend({
    model: bookmarkApp.collections.BookmarkListCollection,
    el: $('#bookmarkList'),
    initialize: function() {
    	var self = this;
    	this.model = new bookmarkApp.collections.BookmarkListCollection();
        this.model.fetch({
        	success: function() {
        		console.log(self.model);
        		self.render();
        	},
        	error: function() {
        		console.log('Cannot retrieve models from server');
        	}
        });
        this.model.on('add', this.render, this);  
        this.model.on('remove', this.render, this);  
    },
    
    render: function() {
    	var self = this;
        self.$el.html('');
        _.each(this.model.toArray(), function(bookmarkModel, i) {
        	self.$el.append((new bookmarkApp.views.BookmarkView({model: bookmarkModel})).render().$el);
        });
        return this;
    }
});

bookmarkApp.views.TagCountListView = Backbone.View.extend({
    model: bookmarkApp.collections.TagCountListCollection,
    el: $('#tagCountList'),
    initialize: function() {
    	var self = this;
    	this.model = new bookmarkApp.collections.TagCountListCollection(),
        this.model.fetch({
        	success: function() {
        		console.log(self.model);
        		self.render();
        	},
        	error: function() {
        		console.log('Cannot retrieve models from server');
        	}
        });
        this.model.on('add', this.render, this);  
        this.model.on('remove', this.render, this);  
    },
    render: function() {
        var self = this;
        self.$el.html('');
        _.each(this.model.toArray(), function(tagCount, i) {
        	self.$el.append((new bookmarkApp.views.TagCountView({model: tagCount})).render().$el);
        });
        return this;
    },
    refresh: function() {
    	
    }
    
});

bookmarkApp.views.TagCountView = Backbone.View.extend({
    model: bookmarkApp.models.TagCountModel,
    tagName: 'li',
    initialize: function() {
    	this.template = _.template($('#tag-count-template').html());
    },
    render: function() {
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    }
});

$(document).ready(function() {
	_.templateSettings = {
		evaluate:    /\{\{(.+?)\}\}/g,
		interpolate: /\{\{=(.+?)\}\}/g,
		escape:      /\{\{-(.+?)\}\}/g
	};
    var router = new bookmarkApp.routers.AppRouter();
    Backbone.history.start();
});