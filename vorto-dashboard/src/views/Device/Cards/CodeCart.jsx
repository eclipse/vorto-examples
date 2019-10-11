import React, { Component } from 'react'

import SyntaxHighlighter from 'react-syntax-highlighter'
import { anOldHope } from 'react-syntax-highlighter/dist/esm/styles/hljs'

import { getRepositoryLink } from '../../../util'

export class CodeCard extends Component {
    render() {
        const jsonText = JSON.stringify(this.props.feature, null, 2)

        return (
            <div className='card card-stats attr-card'>

                    <a href={getRepositoryLink(this.props.feature.definition)} rel="noopener noreferrer" target='_blank' >
                        <div className='header'>
                            {this.props.featureName}
                        </div>
                    </a>

                    <div className='code-container'>
                        <SyntaxHighlighter className='code' language='json' style={anOldHope}>
                            {jsonText}
                        </SyntaxHighlighter>
                    </div>

                </div>
        )
    }
}

export default CodeCard