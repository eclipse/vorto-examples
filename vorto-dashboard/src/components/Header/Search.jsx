import React from "react";
import { FormControl } from "react-bootstrap";
import { connect } from "react-redux";

import Actions from "../../actions"

const mapStateToProps = (state, props) => {
    return {
        props: props,
        searchQuery: state.search.query
    };
};

function mapDispatchToProps(dispatch) {
    return {
        updateSearch: query => dispatch(Actions.updateSearch(query))
    };
}

function handleSearch(evt, updateSearch) {
    const searchQuery = evt.target.value;
    if (!searchQuery) {
        updateSearch("")
        return
    }

    updateSearch(searchQuery.toLowerCase())
}

const ConnectedSearch = ({ props, searchQuery, updateSearch }) => {
    const searchBar = props.brand !== "Device Dashboard" ?
        <FormControl type="text" value={searchQuery} placeholder="Search..." className="searchBox" onChange={(evt) => handleSearch(evt, updateSearch)} />
        : <div />;

    return searchBar;
}

const Search = connect(mapStateToProps, mapDispatchToProps)(ConnectedSearch)

export default Search;