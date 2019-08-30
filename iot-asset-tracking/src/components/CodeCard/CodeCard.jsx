import React, { Component } from 'react'
import { Row, Col } from 'react-bootstrap'

import SyntaxHighlighter from 'react-syntax-highlighter'
import { anOldHope } from 'react-syntax-highlighter/dist/esm/styles/hljs'

import { getRepositoryLink } from '../../util'

export class CodeCard extends Component {
  render () {
    const jsonText = JSON.stringify(this.props.feature, null, 2)

    return (
      <div className='card card-stats attr-card'>
        <div className='content'>
          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <div className='text-center'>
                <h4>
                  <a href={getRepositoryLink(this.props.feature.definition)} target='_blank' >{this.props.featureName}</a>
                </h4>
              </div>
            </Col>
          </Row>

          <Row>
            <Col xs={12} sm={12} md={12} lg={12}>
              <div className=''>
                <SyntaxHighlighter language='json' style={anOldHope}>
                  {jsonText}
                </SyntaxHighlighter>
              </div>
            </Col>
          </Row>
        </div>
      </div>
    )
  }
}

export default CodeCard
