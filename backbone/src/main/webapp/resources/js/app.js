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

bookmarkApp.views.BookmarkTagView = Backbone.View.extend({
    model: bookmarkApp.models.TagModel,
    tagName: 'span',
    initialize: function() {
    	this.template = _.template($('#bookmarkTagList_template').html());
    },
    render: function() {
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    }
});

bookmarkApp.views.BookmarkView = Backbone.View.extend({
    model: bookmarkApp.models.BookmarkModel,
    tagName: 'li',
    initialize: function() {
    	this.template = _.template($('#bookmark_template').html());
    },
    render: function() {
        this.$el.html(this.template(this.model.toJSON()));
        var tags = this.model.get('tags');
        if (tags.length > 0) {
        	this.$el.append(' | ');
        	for (var i = 0; i < tags.length; i++) {
        		var tagModel = new bookmarkApp.models.TagModel({tag: tags[i].tag});
        		this.$el.append((new bookmarkApp.views.BookmarkTagView({model: tagModel})).render().$el);
        		if (i < tags.length - 1) {
        			this.$el.append(', ');
        		}
        	}
        }
        return this;
    }
});

bookmarkApp.views.BookmarkListView = Backbone.View.extend({
    model: new bookmarkApp.collections.BookmarkListCollection(),
    el: $('#bookmarkList'),
    initialize: function() {
    	var self = this;
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
    model: new bookmarkApp.collections.TagCountListCollection(),
    el: $('#tagCountList'),
    initialize: function() {
    	var self = this;
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
    }
    
});

bookmarkApp.views.TagCountView = Backbone.View.extend({
    model: bookmarkApp.models.TagCountModel,
    tagName: 'li',
    initialize: function() {
    	this.template = _.template($('#tagCount_template').html());
    },
    render: function() {
        this.$el.html(this.template(this.model.toJSON()));
        return this;
    }
});


Backbone.history.start({
    pushState: true/false,
    root: ''
});

$(document).ready(function() {
	_.templateSettings = {
		interpolate: /\{\{(.+?)\}\}/g
	};
    var tagCountListView = new bookmarkApp.views.TagCountListView();
    var bookmarkListView = new bookmarkApp.views.BookmarkListView();
});